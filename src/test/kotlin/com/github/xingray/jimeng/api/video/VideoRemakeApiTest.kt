package com.github.xingray.jimeng.api.video

import com.github.xingray.jimeng.BaseApiTest
import com.github.xingray.jimeng.model.common.TaskStatus
import com.github.xingray.jimeng.model.video.VideoRemakeRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class VideoRemakeApiTest : BaseApiTest() {

    private fun createRequest() = VideoRemakeRequest(videoInput = "https://example.com/video.mp4")

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.videoRemake.submit(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQuery(): Unit = runBlocking {
        val submitResp = client.videoRemake.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.videoRemake.query(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.videoRemake.query(credential, taskId)
            println("query #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }

    @Test
    fun testSubmitAndWait(): Unit = runBlocking {
        val resp = client.videoRemake.submitAndWait(credential, createRequest())
        println("submitAndWait: code=${resp.code}, status=${resp.data?.status}, videoUrl=${resp.data?.videoUrl}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }
}
