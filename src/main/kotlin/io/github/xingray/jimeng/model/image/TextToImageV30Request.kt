package io.github.xingray.jimeng.model.image

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 即梦文生图3.0 请求参数。
 *
 * 通过文本描述生成高质量图像。
 */
@Serializable
data class TextToImageV30Request(
    /** 请求类型标识，固定值 `jimeng_t2i_v30` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.TEXT_TO_IMAGE_V30,
    /** 文本提示词，描述要生成的图像内容 */
    @SerialName("prompt")
    val prompt: String,
    /** 生成图像的宽度（像素） */
    @SerialName("width")
    val width: Int? = null,
    /** 生成图像的高度（像素） */
    @SerialName("height")
    val height: Int? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null,
    /** 是否使用大模型优化提示词 */
    @SerialName("use_pre_llm")
    val usePreLlm: Boolean? = null
)
