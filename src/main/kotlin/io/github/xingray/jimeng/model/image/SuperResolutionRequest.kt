package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 智能高清（图像超分辨率）请求参数。
 *
 * 将低分辨率图像放大至高分辨率，同时保持图像质量。
 */
@Serializable
data class SuperResolutionRequest(
    /** 请求类型标识，固定值 `jimeng_i2i_seed3_tilesr_cvtob` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.SUPER_RESOLUTION,
    /** 待处理图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 待处理图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 目标分辨率 */
    @SerialName("resolution")
    val resolution: String? = null,
    /** 放大倍率 */
    @SerialName("scale")
    val scale: Float? = null
)
