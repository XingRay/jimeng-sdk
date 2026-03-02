package io.github.xingray.jimeng.api.video

import io.github.xingray.jimeng.JimengConstants
import io.github.xingray.jimeng.api.VolcApiClient
import io.github.xingray.jimeng.auth.Credential
import io.github.xingray.jimeng.model.common.*
import io.github.xingray.jimeng.model.video.VideoProV30T2VRequest
import kotlinx.coroutines.delay

/**
 * 即梦视频3.0 Pro 文生视频 API。
 *
 * 通过文本描述生成高质量视频，支持提交任务并轮询获取视频结果。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class VideoProV30Api(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {
    private val reqKey = ReqKey.VIDEO_T2V_PRO_V30

    /** 提交文生视频Pro任务 */
    suspend fun submit(credential: Credential, request: VideoProV30T2VRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 查询视频生成任务结果 */
    suspend fun query(credential: Credential, taskId: String, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val reqJson = aigcMeta?.let { client.json.encodeToString(VideoQueryReqJson.serializer(), VideoQueryReqJson(it)) }
        return client.post(credential, JimengConstants.ACTION_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 提交任务并轮询等待结果，返回视频结果 */
    suspend fun submitAndWait(credential: Credential, request: VideoProV30T2VRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
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
