package com.github.xingray.jimeng.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * AIGC合规标识信息，用于标注AI生成内容，符合国家法规要求
 *
 * @property contentProducer 内容生产者名称
 * @property producerId 内容生产者ID
 * @property contentPropagator 内容传播者名称
 * @property propagateId 内容传播者ID
 */
@Serializable
data class AigcMeta(
    @SerialName("content_producer")
    val contentProducer: String = "",
    @SerialName("producer_id")
    val producerId: String = "",
    @SerialName("content_propagator")
    val contentPropagator: String = "",
    @SerialName("propagate_id")
    val propagateId: String = ""
)
