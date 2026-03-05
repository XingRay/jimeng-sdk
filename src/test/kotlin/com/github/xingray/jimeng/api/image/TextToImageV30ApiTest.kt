package com.github.xingray.jimeng.api.image

import com.github.xingray.jimeng.BaseApiTest
import com.github.xingray.jimeng.model.common.TaskStatus
import com.github.xingray.jimeng.model.image.TextToImageV30Request
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TextToImageV30ApiTest : BaseApiTest() {

    private fun createRequest() = TextToImageV30Request(prompt = "一只橘色的猫咪", width = 512, height = 512)

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.textToImageV30.submit(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQueryUrl(): Unit = runBlocking {
        val submitResp = client.textToImageV30.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.textToImageV30.queryUrl(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.textToImageV30.queryUrl(credential, taskId)
            println("queryUrl #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.imageUrls?.isNotEmpty() == true)
    }

    @Test
    fun testQueryBase64(): Unit = runBlocking {
        val submitResp = client.textToImageV30.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.textToImageV30.queryBase64(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.textToImageV30.queryBase64(credential, taskId)
            println("queryBase64 #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.binaryDataBase64?.isNotEmpty() == true)
    }

    @Test
    fun testSubmitAndWaitForUrl(): Unit = runBlocking {
        val resp = client.textToImageV30.submitAndWaitForUrl(credential, createRequest())
        println("submitAndWaitForUrl: code=${resp.code}, status=${resp.data?.status}, urls=${resp.data?.imageUrls}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.imageUrls?.isNotEmpty() == true)
    }

    @Test
    fun testSubmitAndWaitForBase64(): Unit = runBlocking {
        val resp = client.textToImageV30.submitAndWaitForBase64(credential, createRequest())
        println("submitAndWaitForBase64: code=${resp.code}, status=${resp.data?.status}, count=${resp.data?.binaryDataBase64?.size}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.binaryDataBase64?.isNotEmpty() == true)
    }
}
