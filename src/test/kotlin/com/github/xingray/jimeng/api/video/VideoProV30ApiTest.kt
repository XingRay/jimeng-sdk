package com.github.xingray.jimeng.api.video

import com.github.xingray.jimeng.BaseApiTest
import com.github.xingray.jimeng.model.common.TaskStatus
import com.github.xingray.jimeng.model.video.VideoProV30T2VRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class VideoProV30ApiTest : BaseApiTest() {

    private fun createRequest() = VideoProV30T2VRequest(prompt = "海浪拍打沙滩的慢镜头", frames = 121)

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.videoProV30.submit(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQuery(): Unit = runBlocking {
        val submitResp = client.videoProV30.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.videoProV30.query(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.videoProV30.query(credential, taskId)
            println("query #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }

    @Test
    fun testSubmitAndWait(): Unit = runBlocking {
        val resp = client.videoProV30.submitAndWait(credential, createRequest())
        println("submitAndWait: code=${resp.code}, status=${resp.data?.status}, videoUrl=${resp.data?.videoUrl}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }
}
