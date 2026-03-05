package com.github.xingray.jimeng.api.video

import com.github.xingray.jimeng.JimengConstants
import com.github.xingray.jimeng.api.VolcApiClient
import com.github.xingray.jimeng.auth.Credential
import com.github.xingray.jimeng.model.common.*
import com.github.xingray.jimeng.model.video.Video720pI2VFirstRequest
import com.github.xingray.jimeng.model.video.Video720pI2VFirstTailRequest
import com.github.xingray.jimeng.model.video.Video720pI2VRecameraRequest
import com.github.xingray.jimeng.model.video.Video720pT2VRequest
import kotlinx.coroutines.delay

/**
 * 即梦视频3.0 720P API。
 *
 * 支持文生视频和图生视频（首帧/首尾帧/运镜），生成720P分辨率视频。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class Video720pApi(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {

    /** 提交720P文生视频任务 */
    suspend fun submitT2V(credential: Credential, request: Video720pT2VRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 提交720P图生视频任务（首帧模式） */
    suspend fun submitI2VFirst(credential: Credential, request: Video720pI2VFirstRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 提交720P图生视频任务（首尾帧模式） */
    suspend fun submitI2VFirstTail(credential: Credential, request: Video720pI2VFirstTailRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 提交720P图生视频任务（运镜模式） */
    suspend fun submitI2VRecamera(credential: Credential, request: Video720pI2VRecameraRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_SUBMIT_TASK, request)
    }

    /** 查询视频生成任务结果 */
    suspend fun query(credential: Credential, reqKey: String, taskId: String, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val reqJson = aigcMeta?.let { client.json.encodeToString(VideoQueryReqJson.serializer(), VideoQueryReqJson(it)) }
        return client.post(credential, JimengConstants.ACTION_GET_RESULT, QueryTaskRequest(reqKey, taskId, reqJson))
    }

    /** 提交文生视频任务并轮询等待结果 */
    suspend fun submitAndWaitT2V(credential: Credential, request: Video720pT2VRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitT2V(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.VIDEO_T2V_720P_V30, taskId, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交图生视频（首帧）任务并轮询等待结果 */
    suspend fun submitAndWaitI2VFirst(credential: Credential, request: Video720pI2VFirstRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitI2VFirst(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.VIDEO_I2V_FIRST_720P_V30, taskId, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交图生视频（首尾帧）任务并轮询等待结果 */
    suspend fun submitAndWaitI2VFirstTail(credential: Credential, request: Video720pI2VFirstTailRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitI2VFirstTail(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.VIDEO_I2V_FIRST_TAIL_720P_V30, taskId, aigcMeta)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交图生视频（运镜）任务并轮询等待结果 */
    suspend fun submitAndWaitI2VRecamera(credential: Credential, request: Video720pI2VRecameraRequest, aigcMeta: AigcMeta? = null): ApiResponse<VideoResult> {
        val submitResp = submitI2VRecamera(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.VIDEO_I2V_RECAMERA_720P_V30, taskId, aigcMeta)
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
