package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 动漫风格转换请求参数。
 *
 * 将普通图片转换为动漫风格图像。
 */
@Serializable
data class AnimeStyleRequest(
    /** 请求类型标识，固定值 `img2img_anime_jimeng` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.ANIME_STYLE,
    /** 风格描述提示词 */
    @SerialName("prompt")
    val prompt: String,
    /** 原始图片URL */
    @SerialName("image_url")
    val imageUrl: String? = null,
    /** 原始图片Base64编码列表 */
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    /** 风格转换强度 */
    @SerialName("strength")
    val strength: Float? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null
)
