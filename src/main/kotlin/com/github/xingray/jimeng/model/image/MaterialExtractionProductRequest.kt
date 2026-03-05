package com.github.xingray.jimeng.model.image

import com.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 素材抽取-商品图请求参数。
 *
 * 从图片中抽取商品素材，生成商品展示图。
 */
@Serializable
data class MaterialExtractionProductRequest(
    /** 请求类型标识，固定值 `jimeng_i2i_extract_tiled_images` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.MATERIAL_EXTRACTION_PRODUCT,
    /** 编辑提示词 */
    @SerialName("edit_prompt")
    val editPrompt: String,
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
    val seed: Int? = null
)
