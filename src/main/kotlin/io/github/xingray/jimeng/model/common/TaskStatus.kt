package io.github.xingray.jimeng.model.common

/**
 * 异步任务状态常量
 */
object TaskStatus {
    /** 排队中 */
    const val IN_QUEUE = "in_queue"
    /** 生成中 */
    const val GENERATING = "generating"
    /** 处理中 */
    const val PROCESSING = "processing"
    /** 已完成 */
    const val DONE = "done"
    /** 未找到 */
    const val NOT_FOUND = "not_found"
    /** 已过期 */
    const val EXPIRED = "expired"
}
