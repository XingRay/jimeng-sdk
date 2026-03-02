package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 写真照片（AI换脸）请求参数。
 *
 * 基于模板和人脸图片进行AI换脸，生成写真风格照片。
 */
@Serializable
data class PortraitPhotoRequest(
    /** 请求类型标识，固定值 `i2i_single_faceswap_jimeng` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.PORTRAIT_PHOTO,
    /** 输入图片URL列表（模板+人脸） */
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    /** 输入图片Base64编码列表（模板+人脸） */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 面部增强强度 */
    @SerialName("gpen")
    val gpen: Float? = null,
    /** 皮肤美化强度 */
    @SerialName("skin")
    val skin: Float? = null
)
