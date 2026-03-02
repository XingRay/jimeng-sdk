package io.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 图像生成结果（Base64模式）
 *
 * @property status 任务状态
 * @property taskId 任务ID
 * @property binaryDataBase64 生成图像的Base64编码列表
 * @property respData 附加响应数据
 */
@Serializable
data class ImageBase64Result(
    @SerialName("status")
    val status: String = "",
    @SerialName("task_id")
    val taskId: String = "",
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("resp_data")
    val respData: String? = null
)
