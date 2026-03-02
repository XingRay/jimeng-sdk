package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 即梦互动编辑（图像修复/局部重绘）请求参数。
 *
 * 通过提示词对图像进行局部修复或重绘。
 */
@Serializable
data class InpaintingRequest(
    /** 请求类型标识，固定值 `jimeng_image2image_dream_inpaint` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.INPAINTING,
    /** 编辑提示词，描述要修复或重绘的内容 */
    @SerialName("prompt")
    val prompt: String,
    /** 原始图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 原始图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null
)
