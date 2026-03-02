package io.github.xingray.jimeng.api.image

import io.github.xingray.jimeng.BaseApiTest
import io.github.xingray.jimeng.model.common.TaskStatus
import io.github.xingray.jimeng.model.image.MarketingProductImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MarketingProductImageApiTest : BaseApiTest() {

    private fun createRequest() = MarketingProductImageRequest(prompt = "商品展示图")

    @Test
    fun testSubmit(): Unit = runBlocking {
        val resp = client.marketingProductImage.submit(credential, createRequest())
        println("submit: code=${resp.code}, taskId=${resp.data?.taskId}")
        assertTrue(resp.isSuccess, "submit failed: ${resp.message}")
        assertNotNull(resp.data?.taskId)
    }

    @Test
    fun testQueryUrl(): Unit = runBlocking {
        val submitResp = client.marketingProductImage.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.marketingProductImage.queryUrl(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.marketingProductImage.queryUrl(credential, taskId)
            println("queryUrl #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.imageUrls?.isNotEmpty() == true)
    }

    @Test
    fun testQueryBase64(): Unit = runBlocking {
        val submitResp = client.marketingProductImage.submit(credential, createRequest())
        assertTrue(submitResp.isSuccess, "submit failed: ${submitResp.message}")
        val taskId = submitResp.data!!.taskId

        var attempts = 0
        var resp = client.marketingProductImage.queryBase64(credential, taskId)
        while (resp.isSuccess && resp.data?.status.let { it == TaskStatus.IN_QUEUE || it == TaskStatus.GENERATING || it == TaskStatus.PROCESSING }) {
            check(++attempts < 200) { "polling timed out" }
            delay(3000L)
            resp = client.marketingProductImage.queryBase64(credential, taskId)
            println("queryBase64 #$attempts: status=${resp.data?.status}")
        }

        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.binaryDataBase64?.isNotEmpty() == true)
    }

    @Test
    fun testSubmitAndWaitForUrl(): Unit = runBlocking {
        val resp = client.marketingProductImage.submitAndWaitForUrl(credential, createRequest())
        println("submitAndWaitForUrl: code=${resp.code}, status=${resp.data?.status}, urls=${resp.data?.imageUrls}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.imageUrls?.isNotEmpty() == true)
    }

    @Test
    fun testSubmitAndWaitForBase64(): Unit = runBlocking {
        val resp = client.marketingProductImage.submitAndWaitForBase64(credential, createRequest())
        println("submitAndWaitForBase64: code=${resp.code}, status=${resp.data?.status}, count=${resp.data?.binaryDataBase64?.size}")
        assertTrue(resp.isSuccess, "failed: ${resp.message}")
        assertEquals(TaskStatus.DONE, resp.data?.status)
        assertTrue(resp.data?.binaryDataBase64?.isNotEmpty() == true)
    }
}
