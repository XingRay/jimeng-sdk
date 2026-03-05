package com.github.xingray.jimeng.api.digital

import com.github.xingray.jimeng.JimengConstants
import com.github.xingray.jimeng.api.VolcApiClient
import com.github.xingray.jimeng.auth.Credential
import com.github.xingray.jimeng.model.common.*
import com.github.xingray.jimeng.model.digital.OmniHumanV15DetectionRequest
import com.github.xingray.jimeng.model.digital.OmniHumanV15RecognizeRequest
import com.github.xingray.jimeng.model.digital.OmniHumanV15VideoRequest
import kotlinx.coroutines.delay

/**
 * OmniHuman 1.5 数字人 API。
 *
 * 分三步生成数字人视频：主体识别、主体检测和视频生成。
 *
 * @param client 火山引擎API客户端
 * @param pollIntervalMs 轮询间隔时间（毫秒）
 * @param maxPollAttempts 最大轮询次数
 */
class OmniHumanV15Api(
    private val client: VolcApiClient,
    private val pollIntervalMs: Long = 3000L,
    private val maxPollAttempts: Int = 200
) {

    /** 提交主体识别任务 */
    suspend fun submitRecognize(credential: Credential, request: OmniHumanV15RecognizeRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_CV_SUBMIT_TASK, request)
    }

    /** 执行主体检测（同步） */
    suspend fun detection(credential: Credential, request: OmniHumanV15DetectionRequest): ApiResponse<ProcessResult> {
        return client.post(credential, JimengConstants.ACTION_CV_PROCESS, request)
    }

    /** 提交数字人视频生成任务 */
    suspend fun submitVideo(credential: Credential, request: OmniHumanV15VideoRequest): ApiResponse<SubmitTaskData> {
        return client.post(credential, JimengConstants.ACTION_CV_SUBMIT_TASK, request)
    }

    /** 查询任务结果 */
    suspend fun query(credential: Credential, reqKey: String, taskId: String): ApiResponse<VideoResult> {
        return client.post(credential, JimengConstants.ACTION_CV_GET_RESULT, QueryTaskRequest(reqKey, taskId))
    }

    /** 提交主体识别任务并轮询等待结果 */
    suspend fun submitAndWaitRecognize(credential: Credential, request: OmniHumanV15RecognizeRequest): ApiResponse<VideoResult> {
        val submitResp = submitRecognize(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.OMNIHUMAN_V15_RECOGNIZE, taskId)
            val status = result.data?.status
            when {
                !result.isSuccess -> return result
                status == TaskStatus.DONE || status == TaskStatus.NOT_FOUND || status == TaskStatus.EXPIRED -> return result
                else -> delay(pollIntervalMs)
            }
        }
        return ApiResponse(code = -1, message = "Polling timeout after $maxPollAttempts attempts for task: $taskId")
    }

    /** 提交数字人视频生成任务并轮询等待结果 */
    suspend fun submitAndWaitVideo(credential: Credential, request: OmniHumanV15VideoRequest): ApiResponse<VideoResult> {
        val submitResp = submitVideo(credential, request)
        if (!submitResp.isSuccess || submitResp.data == null) {
            return ApiResponse(code = submitResp.code, message = submitResp.message, requestId = submitResp.requestId)
        }
        val taskId = submitResp.data.taskId
        repeat(maxPollAttempts) {
            val result = query(credential, ReqKey.OMNIHUMAN_V15_VIDEO, taskId)
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
