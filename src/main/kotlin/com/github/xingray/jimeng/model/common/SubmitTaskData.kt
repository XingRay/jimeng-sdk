package com.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 异步任务提交响应数据
 *
 * @property taskId 异步任务ID，用于后续查询任务结果
 */
@Serializable
data class SubmitTaskData(
    @SerialName("task_id")
    val taskId: String = ""
)
