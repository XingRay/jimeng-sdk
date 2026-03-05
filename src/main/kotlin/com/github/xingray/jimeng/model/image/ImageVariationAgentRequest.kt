package com.github.xingray.jimeng.model.image

import com.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 图片变体智能体请求参数。
 *
 * 基于输入图片生成变体，支持文字覆盖和贴纸覆盖等高级功能。
 */
@Serializable
data class ImageVariationAgentRequest(
    /** 请求类型标识，固定值 `i2i_ads_agent` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.IMAGE_VARIATION_AGENT,
    /** 输入图片URL */
    @SerialName("image_input_url")
    val imageInputUrl: String? = null,
    /** 用户提示词 */
    @SerialName("user_prompt")
    val userPrompt: String? = null,
    /** 负面提示词，描述不希望出现的内容 */
    @SerialName("negative_prompt")
    val negativePrompt: String? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null,
    /** 输出图片宽度（像素） */
    @SerialName("img_width")
    val imgWidth: Int? = null,
    /** 输出图片高度（像素） */
    @SerialName("img_height")
    val imgHeight: Int? = null,
    /** 是否启用文字覆盖 */
    @SerialName("text_switch")
    val textSwitch: Boolean? = null,
    /** 是否启用贴纸覆盖 */
    @SerialName("sticker_switch")
    val stickerSwitch: Boolean? = null,
    /** 嵌入文字内容 */
    @SerialName("embed_text")
    val embedText: String? = null,
    /** 文字框宽度（像素） */
    @SerialName("textbox_width")
    val textboxWidth: Int? = null,
    /** 文字框高度（像素） */
    @SerialName("textbox_height")
    val textboxHeight: Int? = null,
    /** 文字最大字号 */
    @SerialName("text_max_size")
    val textMaxSize: Int? = null,
    /** 文字颜色 */
    @SerialName("text_color")
    val textColor: String? = null,
    /** 文字起始X坐标 */
    @SerialName("text_start_x")
    val textStartX: Int? = null,
    /** 文字起始Y坐标 */
    @SerialName("text_start_y")
    val textStartY: Int? = null,
    /** 字体名称 */
    @SerialName("text_font")
    val textFont: String? = null,
    /** 文字对齐方式 */
    @SerialName("text_alignment")
    val textAlignment: String? = null,
    /** 文字行高 */
    @SerialName("text_line_height")
    val textLineHeight: Int? = null,
    /** 深色贴纸图片URL */
    @SerialName("sticker_dark_upload")
    val stickerDarkUpload: String? = null,
    /** 浅色贴纸图片URL */
    @SerialName("sticker_bright_upload")
    val stickerBrightUpload: String? = null,
    /** 贴纸X轴偏移量 */
    @SerialName("sticker_offset_x")
    val stickerOffsetX: Int? = null,
    /** 贴纸Y轴偏移量 */
    @SerialName("sticker_offset_y")
    val stickerOffsetY: Int? = null,
    /** 贴纸缩放比例 */
    @SerialName("sticker_scale")
    val stickerScale: Float? = null,
    /** 贴纸原点类型 */
    @SerialName("sticker_origintype")
    val stickerOriginType: String? = null,
    /** 贴纸起始X坐标 */
    @SerialName("sticker_start_x")
    val stickerStartX: Int? = null,
    /** 贴纸起始Y坐标 */
    @SerialName("sticker_start_y")
    val stickerStartY: Int? = null
)
