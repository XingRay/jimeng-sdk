package io.github.xingray.jimeng.api.video

import io.github.xingray.jimeng.BaseApiTest
import io.github.xingray.jimeng.model.common.ReqKey
import io.github.xingray.jimeng.model.common.TaskStatus
import io.github.xingray.jimeng.model.video.Video720pT2VRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class Video720pApiTest : BaseApiTest() {

    private fun createRequest() = Video720pT2VRequest(prompt = "日落时分的城市天际线")

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.video720p.submitT2V(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQuery(): Unit = runBlocking {
        val submitResp = client.video720p.submitT2V(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.video720p.query(credential, ReqKey.VIDEO_T2V_720P_V30, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.video720p.query(credential, ReqKey.VIDEO_T2V_720P_V30, taskId)
            println("query #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }

    @Test
    fun testSubmitAndWait(): Unit = runBlocking {
        val resp = client.video720p.submitAndWaitT2V(credential, createRequest())
        println("submitAndWait: code=${resp.code}, status=${resp.data?.status}, videoUrl=${resp.data?.videoUrl}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }
}
