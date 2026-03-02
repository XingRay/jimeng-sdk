package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 即梦图生图3.0 智能参考请求参数。
 *
 * 基于参考图片和文本提示词生成新图像，支持智能参考控制。
 */
@Serializable
data class ImageToImageSmartRefRequest(
    /** 请求类型标识，固定值 `jimeng_high_aes_general_v31` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.IMAGE_TO_IMAGE_SMART_REF_V31,
    /** 文本提示词，描述要生成的图像内容 */
    @SerialName("prompt")
    val prompt: String,
    /** 参考图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 参考图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 生成图像的宽度（像素） */
    @SerialName("width")
    val width: Int? = null,
    /** 生成图像的高度（像素） */
    @SerialName("height")
    val height: Int? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null,
    /** 参考图片影响强度 */
    @SerialName("scale")
    val scale: Float? = null,
    /** 是否使用大模型优化提示词 */
    @SerialName("use_pre_llm")
    val usePreLlm: Boolean? = null
)
