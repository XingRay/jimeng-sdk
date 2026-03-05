package com.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 水印配置信息
 *
 * @property addLogo 是否添加水印
 * @property position 水印位置
 * @property language 水印语言（0=中文，1=英文）
 * @property opacity 水印透明度
 * @property logoTextContent 水印文字内容
 */
@Serializable
data class LogoInfo(
    @SerialName("add_logo")
    val addLogo: Boolean = false,
    @SerialName("position")
    val position: Int = 0,
    @SerialName("language")
    val language: Int = 0,
    @SerialName("opacity")
    val opacity: Float = 1.0f,
    @SerialName("logo_text_content")
    val logoTextContent: String = ""
)
