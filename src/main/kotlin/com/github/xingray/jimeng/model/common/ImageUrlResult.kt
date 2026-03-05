package com.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 图像生成结果（URL模式）
 *
 * @property status 任务状态（in_queue/generating/done/not_found/expired）
 * @property taskId 任务ID
 * @property imageUrls 生成图像的URL列表（有效期1小时）
 * @property respData 附加响应数据
 */
@Serializable
data class ImageUrlResult(
    @SerialName("status")
    val status: String = "",
    @SerialName("task_id")
    val taskId: String = "",
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("resp_data")
    val respData: String? = null
)
