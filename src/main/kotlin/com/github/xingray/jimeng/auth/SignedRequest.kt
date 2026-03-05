package com.github.xingray.jimeng.auth

/**
 * 签名结果，包含发起请求所需的所有签名信息。
 */
data class SignedRequest(
    val xDate: String,
    val xContentSha256: String,
    val authorization: String,
    val queryString: String,
    val contentType: String
)
