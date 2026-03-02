package io.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 异步任务查询请求
 *
 * @property reqKey 请求类型标识，需与提交任务时一致
 * @property taskId 待查询的任务ID
 * @property reqJson 查询附加参数（JSON字符串）
 */
@Serializable
data class QueryTaskRequest(
    @SerialName("req_key")
    val reqKey: String,
    @SerialName("task_id")
    val taskId: String,
    @SerialName("req_json")
    val reqJson: String? = null
)

/**
 * 图像URL查询参数
 *
 * @property returnUrl 是否返回图片URL，固定 true
 * @property logoInfo 水印配置
 * @property aigcMeta AIGC合规标识信息
 */
@Serializable
data class ImageUrlQueryReqJson(
    @SerialName("return_url")
    val returnUrl: Boolean = true,
    @SerialName("logo_info")
    val logoInfo: LogoInfo? = null,
    @SerialName("aigc_meta")
    val aigcMeta: AigcMeta? = null
)

/**
 * 图像Base64查询参数
 *
 * @property returnUrl 是否返回图片URL，固定 false（返回Base64编码）
 * @property logoInfo 水印配置
 * @property aigcMeta AIGC合规标识信息
 */
@Serializable
data class ImageBase64QueryReqJson(
    @SerialName("return_url")
    val returnUrl: Boolean = false,
    @SerialName("logo_info")
    val logoInfo: LogoInfo? = null,
    @SerialName("aigc_meta")
    val aigcMeta: AigcMeta? = null
)

/**
 * 视频查询参数
 *
 * @property aigcMeta AIGC合规标识信息
 */
@Serializable
data class VideoQueryReqJson(
    @SerialName("aigc_meta")
    val aigcMeta: AigcMeta? = null
)
