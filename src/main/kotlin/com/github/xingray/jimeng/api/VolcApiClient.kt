package com.github.xingray.jimeng.api

import com.github.xingray.jimeng.JimengConstants
import com.github.xingray.jimeng.auth.Credential
import com.github.xingray.jimeng.auth.VolcSigner
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

/**
 * 火山引擎视觉智能 API 底层 HTTP 客户端。
 *
 * 封装了请求签名、序列化和HTTP通信逻辑。
 *
 * @param httpClient Ktor HTTP客户端实例
 * @param signer 火山引擎请求签名器
 * @param json Kotlinx JSON序列化器
 */
class VolcApiClient(
    @PublishedApi internal val httpClient: HttpClient,
    @PublishedApi internal val signer: com.github.xingray.jimeng.auth.VolcSigner,
    @PublishedApi internal val json: Json
) {

    /** 发送签名POST请求，自动处理请求签名和响应反序列化 */
    suspend inline fun <reified B, reified T> post(
        credential: com.github.xingray.jimeng.auth.Credential, action: String, body: B
    ): T {
        val bodyBytes = json.encodeToString(body).toByteArray(Charsets.UTF_8)
        val signedRequest = signer.sign(credential, "POST", action, bodyBytes)

        val response = httpClient.post("${JimengConstants.BASE_URL}?${signedRequest.queryString}") {
            header("Host", JimengConstants.END_POINT)
            header("X-Date", signedRequest.xDate)
            header("X-Content-Sha256", signedRequest.xContentSha256)
            header("Content-Type", signedRequest.contentType)
            header("Authorization", signedRequest.authorization)
            contentType(ContentType.Application.Json)
            setBody(bodyBytes)
        }

        val jsonBody = response.body<String>()
        println("signedRequest.queryString => \n${jsonBody}\n")
        return json.decodeFromString(jsonBody)
    }
}
