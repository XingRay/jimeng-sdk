package io.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 视频生成结果
 *
 * @property status 任务状态
 * @property taskId 任务ID
 * @property videoUrl 生成视频的URL（有效期1小时）
 * @property aigcMetaTagged 是否已添加AIGC标识水印
 * @property respData 附加响应数据
 */
@Serializable
data class VideoResult(
    @SerialName("status")
    val status: String = "",
    @SerialName("task_id")
    val taskId: String = "",
    @SerialName("video_url")
    val videoUrl: String? = null,
    @SerialName("aigc_meta_tagged")
    val aigcMetaTagged: Boolean? = null,
    @SerialName("resp_data")
    val respData: String? = null
)
