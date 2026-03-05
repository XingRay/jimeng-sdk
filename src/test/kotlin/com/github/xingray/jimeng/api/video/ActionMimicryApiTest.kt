package com.github.xingray.jimeng.api.video

import com.github.xingray.jimeng.BaseApiTest
import com.github.xingray.jimeng.model.common.ReqKey
import com.github.xingray.jimeng.model.common.TaskStatus
import com.github.xingray.jimeng.model.video.ActionMimicryV10Request
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ActionMimicryApiTest : BaseApiTest() {

    private fun createRequest() = ActionMimicryV10Request(videoUrl = "https://example.com/video.mp4", imageUrl = "https://example.com/person.jpg")

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.actionMimicry.submitV10(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQuery(): Unit = runBlocking {
        val submitResp = client.actionMimicry.submitV10(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.actionMimicry.query(credential, ReqKey.ACTION_MIMICRY_V10, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.actionMimicry.query(credential, ReqKey.ACTION_MIMICRY_V10, taskId)
            println("query #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }

    @Test
    fun testSubmitAndWait(): Unit = runBlocking {
        val resp = client.actionMimicry.submitAndWaitV10(credential, createRequest())
        println("submitAndWait: code=${resp.code}, status=${resp.data?.status}, videoUrl=${resp.data?.videoUrl}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }
}
