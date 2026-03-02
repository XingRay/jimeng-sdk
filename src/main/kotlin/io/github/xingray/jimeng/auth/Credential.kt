package io.github.xingray.jimeng.auth

/**
 * A pair of AccessKeyId and SecretAccessKey.
 */
data class Credential(
    val accessKeyId: String,
    val secretAccessKey: String
)
