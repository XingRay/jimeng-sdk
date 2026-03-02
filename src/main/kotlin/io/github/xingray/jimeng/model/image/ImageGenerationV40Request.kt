package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 即梦图像生成4.0 请求参数。
 *
 * 支持文本和参考图片输入，生成高质量图像。
 */
@Serializable
data class ImageGenerationV40Request(
    /** 请求类型标识，固定值 `jimeng_t2i_v40` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.TEXT_TO_IMAGE_V40,
    /** 文本提示词，描述要生成的图像内容 */
    @SerialName("prompt")
    val prompt: String,
    /** 参考图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 生成图像的宽度（像素） */
    @SerialName("width")
    val width: Int? = null,
    /** 生成图像的高度（像素） */
    @SerialName("height")
    val height: Int? = null,
    /** 生成图像数量 */
    @SerialName("size")
    val size: Int? = null,
    /** 文本引导强度 */
    @SerialName("scale")
    val scale: Float? = null,
    /** 是否强制生成单张图片 */
    @SerialName("force_single")
    val forceSingle: Boolean? = null,
    /** 最小宽高比 */
    @SerialName("min_ratio")
    val minRatio: Float? = null,
    /** 最大宽高比 */
    @SerialName("max_ratio")
    val maxRatio: Float? = null
)
