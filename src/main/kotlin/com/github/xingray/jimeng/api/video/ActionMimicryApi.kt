package com.github.xingray.jimeng.api.video

import com.github.xingray.jimeng.JimengConstants
import com.github.xingray.jimeng.api.VolcApiClient
import com.github.xingray.jimeng.auth.Credential
import com.github.xingray.jimeng.model.common.*
import com.github.xingray.jimeng.model.video.ActionMimicryV10Request
import com.github.xingray.jimeng.model.video.ActionMimicryV20Request
import kotlinx.coroutines.delay

/**
 * 动作模仿 API。
 *
 * 将参考视频中的动作迁移到目标人物，支持V1.0和V2.0两个版本。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class ActionMimicryApi(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {

    /** 提交动作模仿V1.0任务 */
    suspend fun submitV10(credential: Credential, request: ActionMimicryV10Request): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 提交动作模仿V2.0任务 */
    suspend fun submitV20(credential: Credential, request: ActionMimicryV20Request): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 查询动作模仿任务结果 */
    suspend fun query(credential: Credential, reqKey: String, taskId: String, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val reqJson = aigcMeta?.let { client.json.encodeToString(VideoQueryReqJson.serializer(), VideoQueryReqJson(it)) }
        return client.post(credential, JimengConstants.ACTION_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 提交动作模仿V1.0任务并轮询等待结果 */
    suspend fun submitAndWaitV10(credential: Credential, request: ActionMimicryV10Request, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitV10(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.ACTION_MIMICRY_V10, taskId, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交动作模仿V2.0任务并轮询等待结果 */
    suspend fun submitAndWaitV20(credential: Credential, request: ActionMimicryV20Request, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitV20(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.ACTION_MIMICRY_V20, taskId, aigcMeta)
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
