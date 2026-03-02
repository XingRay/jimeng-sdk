package io.github.xingray.jimeng.auth

import io.github.xingray.jimeng.JimengConstants
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Volcengine API V4 Signature Signer.
 * 基于 Volcengine HMAC-SHA256 签名机制
 * 文档：https://www.volcengine.com/docs/6369/67268
 */
class VolcSigner(
    private val region: String = JimengConstants.DEFAULT_REGION,
    private val service: String = JimengConstants.DEFAULT_SERVICE
) {

    fun sign(
        credential: Credential,
        method: String,
        action: String,
        body: ByteArray
    ): SignedRequest {
        val host = JimengConstants.END_POINT
        val path = "/"

        // 1. 生成 X-Date
        val sdf = SimpleDateFormat(JimengConstants.DATE_TIME_FORMAT)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        val xDate = sdf.format(Date())
        val shortXDate = xDate.substring(0, 8)

        // 2. 生成 X-Content-Sha256
        val xContentSha256 = sha256Hex(body)

        // 3. 构建 Query String
        val queryString = "Action=$action&Version=${JimengConstants.API_VERSION}"

        // 4. 构建 Canonical Headers (按字母序排列)
        val contentType = JimengConstants.CONTENT_TYPE_JSON
        val canonicalHeaders = "content-type:$contentType\nhost:$host\nx-content-sha256:$xContentSha256\nx-date:$xDate\n"

        // 5. 构建 Canonical Request
        val signedHeaders = JimengConstants.SIGNED_HEADERS
        val canonicalRequest = listOf(
            method.uppercase(),
            path,
            queryString,
            canonicalHeaders,
            signedHeaders,
            xContentSha256
        ).joinToString("\n")

        // 6. 生成签名
        val hashedCanonicalRequest = sha256Hex(canonicalRequest.toByteArray(Charsets.UTF_8))
        val credentialScope = "$shortXDate/$region/$service/request"
        val stringToSign = listOf(
            JimengConstants.SIGNING_KEY_PREFIX,
            xDate,
            credentialScope,
            hashedCanonicalRequest
        ).joinToString("\n")

        val kDate = hmacSha256(credential.secretAccessKey.toByteArray(Charsets.UTF_8), shortXDate)
        val kRegion = hmacSha256(kDate, region)
        val kService = hmacSha256(kRegion, service)
        val kSigning = hmacSha256(kService, "request")
        val signature = hmacSha256(kSigning, stringToSign).toHexString()

        // 7. 构建 Authorization Header
        val authorization =
            "${JimengConstants.SIGNING_KEY_PREFIX} Credential=${credential.accessKeyId}/$credentialScope, SignedHeaders=$signedHeaders, Signature=$signature"

        return SignedRequest(
            xDate = xDate,
            xContentSha256 = xContentSha256,
            authorization = authorization,
            queryString = queryString,
            contentType = contentType
        )
    }

    private fun hmacSha256(key: ByteArray, data: String): ByteArray {
        val mac = Mac.getInstance(JimengConstants.SIGNING_ALGORITHM)
        mac.init(SecretKeySpec(key, JimengConstants.SIGNING_ALGORITHM))
        return mac.doFinal(data.toByteArray(Charsets.UTF_8))
    }

    private fun sha256Hex(data: ByteArray): String {
        val digest = MessageDigest.getInstance(JimengConstants.HASH_ALGORITHM)
        return digest.digest(data).toHexString()
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) }
    }
}
