package com.github.xingray.jimeng.api.digital

import com.github.xingray.jimeng.BaseApiTest
import com.github.xingray.jimeng.model.common.ReqKey
import com.github.xingray.jimeng.model.common.TaskStatus
import com.github.xingray.jimeng.model.digital.OmniHumanV15DetectionRequest
import com.github.xingray.jimeng.model.digital.OmniHumanV15RecognizeRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class OmniHumanV15ApiTest : BaseApiTest() {

    private fun createRecognizeRequest() = OmniHumanV15RecognizeRequest(imageUrl = "https://example.com/portrait.jpg")

    @Test
    fun testSubmitRecognize(): Unit = runBlocking {
        val resp = client.omniHumanV15.submitRecognize(credential, createRecognizeRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testDetection(): Unit = runBlocking {
        val request = OmniHumanV15DetectionRequest(imageUrl = "https://example.com/portrait.jpg")
        val resp = client.omniHumanV15.detection(credential, request)
        println("detection: code=${resp.code}, message=${resp.message}")
        assertNotNull(resp.code)
    }

    @Test
    fun testQuery(): Unit = runBlocking {
        val submitResp = client.omniHumanV15.submitRecognize(credential, createRecognizeRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.omniHumanV15.query(credential, ReqKey.OMNIHUMAN_V15_RECOGNIZE, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.omniHumanV15.query(credential, ReqKey.OMNIHUMAN_V15_RECOGNIZE, taskId)
            println("query #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }

    @Test
    fun testSubmitAndWaitRecognize(): Unit = runBlocking {
        val resp = client.omniHumanV15.submitAndWaitRecognize(credential, createRecognizeRequest())
        println("submitAndWait: code=${resp.code}, status=${resp.data?.status}, videoUrl=${resp.data?.videoUrl}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertNotNull(resp.data?.videoUrl)
    }
}
