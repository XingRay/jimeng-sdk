package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 素材抽取-POD（印刷定制）请求参数。
 *
 * 从图片中抽取素材，用于印刷定制场景。
 */
@Serializable
data class MaterialExtractionPodRequest(
    /** 请求类型标识，固定值 `i2i_material_extraction` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.MATERIAL_EXTRACTION_POD,
    /** 图片编辑提示词 */
    @SerialName("image_edit_prompt")
    val imageEditPrompt: String,
    /** 原始图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 原始图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 输出图像的宽度（像素） */
    @SerialName("width")
    val width: Int? = null,
    /** 输出图像的高度（像素） */
    @SerialName("height")
    val height: Int? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null,
    /** LoRA 权重 */
    @SerialName("lora_weight")
    val loraWeight: Float? = null
)
