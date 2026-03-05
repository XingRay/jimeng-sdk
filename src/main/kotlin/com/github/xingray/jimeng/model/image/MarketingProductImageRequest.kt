package com.github.xingray.jimeng.model.image

import com.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AI营销商品图3.0 请求参数。
 *
 * 根据营销场景描述和商品图片，生成营销用途的商品展示图。
 */
@Serializable
data class MarketingProductImageRequest(
    /** 请求类型标识，固定值 `jimeng_ecom_product_img_v30` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.MARKETING_PRODUCT_IMAGE_V30,
    /** 营销场景描述 */
    @SerialName("prompt")
    val prompt: String,
    /** 商品图片URL列表 */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 商品图片Base64编码列表 */
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
