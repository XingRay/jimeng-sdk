package io.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 同步处理结果（如主体检测等同步接口的返回）
 *
 * @property respData 处理结果数据（JSON字符串）
 * @property binaryDataBase64 处理后的图像Base64编码列表
 * @property imageUrls 处理后的图像URL列表
 */
@Serializable
data class ProcessResult(
    @SerialName("resp_data")
    val respData: String = "",
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null
)
