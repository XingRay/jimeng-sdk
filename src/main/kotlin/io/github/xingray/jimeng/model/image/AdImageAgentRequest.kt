package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 广告配图变体智能体请求参数。
 *
 * 基于广告图片和文案描述，生成广告配图变体。
 */
@Serializable
data class AdImageAgentRequest(
    /** 请求类型标识，固定值 `i2i_ad_agent` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.AD_IMAGE_AGENT,
    /** 广告文案/描述 */
    @SerialName("prompt")
    val prompt: String? = null,
    /** 原始广告图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 原始广告图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 输出图片长边像素 */
    @SerialName("long_side")
    val longSide: Int? = null,
    /** 场景描述 */
    @SerialName("scene_prompt")
    val scenePrompt: String? = null
)
