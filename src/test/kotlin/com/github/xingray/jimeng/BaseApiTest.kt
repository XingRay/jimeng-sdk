package com.github.xingray.jimeng

import com.github.xingray.jimeng.auth.Credential
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseApiTest {
    // 使用可空类型替代 lateinit，避免未初始化异常
    protected val client: JimengClient = JimengClient()
    private var _credential: Credential? = null

    @BeforeAll
    fun setup() {
        val ak = System.getenv("JIMENG_AK")
        val sk = System.getenv("JIMENG_SK")

        // 检查环境变量是否存在
        if (ak.isNullOrBlank() || sk.isNullOrBlank()) {
            // 输出友好的警告信息（使用标准日志而非 System.err）
            System.err.println("WARNING: JIMENG_AK and JIMENG_SK environment variables are not set, skipping API tests")
            // 明确跳过测试，不会标记为失败
            assumeTrue(false, "Skipping tests because JIMENG_AK/JIMENG_SK are not configured")
            // 提前返回，避免后续初始化逻辑
            return
        }

        // 仅当环境变量存在时才初始化
        _credential = Credential(ak, sk)
    }

    protected val credential: Credential =
        _credential ?: Credential("", "")
}
