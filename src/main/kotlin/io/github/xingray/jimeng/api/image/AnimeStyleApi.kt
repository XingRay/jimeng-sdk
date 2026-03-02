package io.github.xingray.jimeng.api.image

import io.github.xingray.jimeng.JimengConstants
import io.github.xingray.jimeng.api.VolcApiClient
import io.github.xingray.jimeng.auth.Credential
import io.github.xingray.jimeng.model.common.*
import io.github.xingray.jimeng.model.image.AnimeStyleRequest
import kotlinx.coroutines.delay

/**
 * 动漫风格转换 API。
 *
 * 将图片转换为动漫风格，支持提交任务后通过URL或Base64两种方式获取结果。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class AnimeStyleApi(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {
    private val reqKey = ReqKey.ANIME_STYLE

    /** 提交动漫风格转换任务 */
    suspend fun submit(credential: Credential, request: AnimeStyleRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 查询任务结果，返回图片URL */
    suspend fun queryUrl(
        credential: Credential, taskId: String,
        logoInfo: LogoInfo? = null, aigcMeta: AigcMeta? = null
    ): ApiResponse<ImageUrlResult> {
        val reqJson = client.json.encodeToString(ImageUrlQueryReqJson.serializer(), ImageUrlQueryReqJson(logoInfo = logoInfo, aigcMeta = aigcMeta))
        return client.post(credential, JimengConstants.ACTION_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 查询任务结果，返回图片Base64编码 */
    suspend fun queryBase64(
        credential: Credential, taskId: String,
        logoInfo: LogoInfo? = null, aigcMeta: AigcMeta? = null
    ): ApiResponse<ImageBase64Result> {
        val reqJson = client.json.encodeToString(ImageBase64QueryReqJson.serializer(), ImageBase64QueryReqJson(logoInfo = logoInfo, aigcMeta = aigcMeta))
        return client.post(credential, JimengConstants.ACTION_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 提交任务并轮询等待结果，返回图片URL */
    suspend fun submitAndWaitForUrl(
        credential: Credential, request: AnimeStyleRequest,
        logoInfo: LogoInfo? = null, aigcMeta: AigcMeta? = null
    ): ApiResponse<ImageUrlResult> {
        val submitResp = submit(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = queryUrl(credential, taskId, logoInfo, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交任务并轮询等待结果，返回图片Base64编码 */
    suspend fun submitAndWaitForBase64(
        credential: Credential, request: AnimeStyleRequest,
        logoInfo: LogoInfo? = null, aigcMeta: AigcMeta? = null
    ): ApiResponse<ImageBase64Result> {
        val submitResp = submit(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = queryBase64(credential, taskId, logoInfo, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }
}
