package com.github.xingray.jimeng.model.image

import com.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 小说/短剧封面智能体请求参数。
 *
 * 基于原始封面图片，生成小说或短剧封面图。
 */
@Serializable
data class NovelCoverAgentRequest(
    /** 请求类型标识，固定值 `i2i_short_play_cover_agent` */
    @SerialName("req_key")
    val reqKey: String = ReqKey.NOVEL_COVER_AGENT,
    /** 原始封面图片URL */
    @SerialName("img_url")
    val imgUrl: String,
    /** 输出图像的宽度（像素） */
    @SerialName("width")
    val width: Int? = null,
    /** 输出图像的高度（像素） */
    @SerialName("height")
    val height: Int? = null,
    /** 负面提示词，描述不希望出现的内容 */
    @SerialName("negative_prompt")
    val negativePrompt: String? = null,
    /** 随机种子，用于复现生成结果，-1 表示随机 */
    @SerialName("seed")
    val seed: Int? = null,
    /** 用户提示词 */
    @SerialName("user_prompt")
    val userPrompt: String? = null
)
