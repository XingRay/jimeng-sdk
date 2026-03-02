package io.github.xingray.jimeng

import io.github.xingray.jimeng.auth.Credential
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseApiTest {
    protected lateinit var client: JimengClient
    protected lateinit var credential: Credential

    @BeforeAll
    fun setup() {
        val ak = System.getenv("JIMENG_AK")
        val sk = System.getenv("JIMENG_SK")
        if (ak.isNullOrBlank() || sk.isNullOrBlank()) {
            System.err.println("ERROR: JIMENG_AK and JIMENG_SK environment variables are not set, skipping tests")
            assumeTrue(false, "JIMENG_AK and JIMENG_SK environment variables are not set")
        }
        credential = Credential(ak, sk)
        client = JimengClient()
    }
}
