package io.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 火山引擎视觉智能 API 通用响应封装
 *
 * @param T 响应数据的类型
 * @property code 响应状态码，10000 表示成功
 * @property data 响应数据
 * @property message 响应消息
 * @property requestId 请求唯一标识
 * @property status HTTP 状态码
 * @property timeElapsed 请求耗时
 */
@Serializable
data class ApiResponse<T>(
    val code: Int = 0,
    val data: T? = null,
    val message: String = "",
    @SerialName("request_id")
    val requestId: String = "",
    val status: Int = 0,
    @SerialName("time_elapsed")
    val timeElapsed: String = ""
) {
    val isSuccess: Boolean get() = code == 10000

    fun <R> mapData(transform: (T) -> R): ApiResponse<R> {
        return ApiResponse(
            code = code,
            data = data?.let(transform),
            message = message,
            requestId = requestId,
            status = status,
            timeElapsed = timeElapsed
        )
    }
}
