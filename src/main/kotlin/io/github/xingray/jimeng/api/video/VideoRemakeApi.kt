package io.github.xingray.jimeng.api.video

import io.github.xingray.jimeng.JimengConstants
import io.github.xingray.jimeng.api.VolcApiClient
import io.github.xingray.jimeng.auth.Credential
import io.github.xingray.jimeng.model.common.*
import io.github.xingray.jimeng.model.video.VideoRemakeRequest
import kotlinx.coroutines.delay

/**
 * 视频重绘 API。
 *
 * 对原始视频进行风格重绘，生成新风格的视频。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class VideoRemakeApi(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {
    private val reqKey = ReqKey.VIDEO_REMAKE

    /** 提交视频重绘任务 */
    suspend fun submit(credential: Credential, request: VideoRemakeRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_CV_SUBMIT_TASK, request)
    }

    /** 查询视频重绘任务结果 */
    suspend fun query(credential: Credential, taskId: String, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val reqJson = aigcMeta?.let { client.json.encodeToString(VideoQueryReqJson.serializer(), VideoQueryReqJson(it)) }
        return client.post(credential, JimengConstants.ACTION_CV_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 提交视频重绘任务并轮询等待结果 */
    suspend fun submitAndWait(credential: Credential, request: VideoRemakeRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submit(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, taskId, aigcMeta)
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
