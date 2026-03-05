package com.github.xingray.jimeng

/**
 * 即梦AI SDK 常量定义。
 *
 * 包含API端点、Action名称、区域、签名算法等常量。
 */
object JimengConstants {

    // ==================== API ====================
    /** API端点域名 */
    const val END_POINT = "visual.volcengineapi.com"
    /** API基础URL */
    const val BASE_URL = "https://$END_POINT"
    /** API版本号 */
    const val API_VERSION = "2022-08-31"

    // ==================== Actions ====================
    /** 提交异步任务Action（同步转异步） */
    const val ACTION_SUBMIT_TASK = "CVSync2AsyncSubmitTask"
    /** 获取异步任务结果Action（同步转异步） */
    const val ACTION_GET_RESULT = "CVSync2AsyncGetResult"
    /** 提交CV异步任务Action */
    const val ACTION_CV_SUBMIT_TASK = "CVSubmitTask"
    /** 获取CV异步任务结果Action */
    const val ACTION_CV_GET_RESULT = "CVGetResult"
    /** CV同步处理Action */
    const val ACTION_CV_PROCESS = "CVProcess"

    // ==================== Region & Service ====================
    /** 默认服务区域（华北1） */
    const val DEFAULT_REGION = "cn-north-1"
    /** 默认服务名称 */
    const val DEFAULT_SERVICE = "cv"

    // ==================== Signing ====================
    /** 签名密钥前缀 */
    const val SIGNING_KEY_PREFIX = "HMAC-SHA256"
    /** 签名算法名称 */
    const val SIGNING_ALGORITHM = "HmacSHA256"
    /** 哈希算法名称 */
    const val HASH_ALGORITHM = "SHA-256"
    /** 日期时间格式（用于签名） */
    const val DATE_TIME_FORMAT = "yyyyMMdd'T'HHmmss'Z'"
    /** 日期格式（用于签名） */
    const val DATE_FORMAT = "yyyyMMdd"
    /** 默认Content-Type */
    const val CONTENT_TYPE_JSON = "application/json"
    /** 签名包含的请求头列表 */
    const val SIGNED_HEADERS = "content-type;host;x-content-sha256;x-date"
}
