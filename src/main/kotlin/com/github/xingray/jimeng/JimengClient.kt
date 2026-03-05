package com.github.xingray.jimeng

import com.github.xingray.jimeng.api.VolcApiClient
import com.github.xingray.jimeng.api.digital.DigitalHumanQuickApi
import com.github.xingray.jimeng.api.digital.OmniHumanV15Api
import com.github.xingray.jimeng.api.image.*
import com.github.xingray.jimeng.api.video.*
import com.github.xingray.jimeng.auth.VolcSigner
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * 即梦AI SDK 客户端入口。
 *
 * 提供所有API的访问入口，包括图像生成、视频生成和数字人等功能。
 *
 * @param region 服务区域，默认为华北1（cn-north-1）
 * @param pollIntervalMs 轮询间隔时间（毫秒），默认3000ms
 * @param maxPollAttempts 最大轮询次数，默认200次
 * @param httpClient 自定义HTTP客户端，为null时自动创建
 */
class JimengClient(
    region: String = _root_ide_package_.com.github.xingray.jimeng.JimengConstants.DEFAULT_REGION,
    pollIntervalMs: Long = 3000L,
    maxPollAttempts: Int = 200,
    httpClient: HttpClient? = null
) {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
    }

    private val ownedHttpClient: HttpClient? = if (httpClient == null) {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(this@JimengClient.json)
            }
        }
    } else null

    private val volcClient = _root_ide_package_.com.github.xingray.jimeng.api.VolcApiClient(
        httpClient = httpClient ?: ownedHttpClient!!,
        signer = _root_ide_package_.com.github.xingray.jimeng.auth.VolcSigner(region),
        json = json
    )

    // ==================== Image APIs ====================
    /** 文生图3.0 API */
    val textToImageV30 = _root_ide_package_.com.github.xingray.jimeng.api.image.TextToImageV30Api(volcClient, pollIntervalMs, maxPollAttempts)
    /** 文生图3.1 API */
    val textToImageV31 = _root_ide_package_.com.github.xingray.jimeng.api.image.TextToImageV31Api(volcClient, pollIntervalMs, maxPollAttempts)
    /** 图像生成4.0 API */
    val imageGenerationV40 = _root_ide_package_.com.github.xingray.jimeng.api.image.ImageGenerationV40Api(volcClient, pollIntervalMs, maxPollAttempts)
    /** 图生图智能参考 API */
    val imageToImageSmartRef = _root_ide_package_.com.github.xingray.jimeng.api.image.ImageToImageSmartRefApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 互动编辑（局部重绘）API */
    val inpainting = _root_ide_package_.com.github.xingray.jimeng.api.image.InpaintingApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 智能高清（超分辨率）API */
    val superResolution = _root_ide_package_.com.github.xingray.jimeng.api.image.SuperResolutionApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 素材抽取-POD API */
    val materialExtractionPod = _root_ide_package_.com.github.xingray.jimeng.api.image.MaterialExtractionPodApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 素材抽取-商品图 API */
    val materialExtractionProduct = _root_ide_package_.com.github.xingray.jimeng.api.image.MaterialExtractionProductApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** AI营销商品图3.0 API */
    val marketingProductImage = _root_ide_package_.com.github.xingray.jimeng.api.image.MarketingProductImageApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 写真照片 API */
    val portraitPhoto = _root_ide_package_.com.github.xingray.jimeng.api.image.PortraitPhotoApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 动漫风格转换 API */
    val animeStyle = _root_ide_package_.com.github.xingray.jimeng.api.image.AnimeStyleApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 广告配图变体智能体 API */
    val adImageAgent = _root_ide_package_.com.github.xingray.jimeng.api.image.AdImageAgentApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 小说/短剧封面智能体 API */
    val novelCoverAgent = _root_ide_package_.com.github.xingray.jimeng.api.image.NovelCoverAgentApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 图片变体智能体 API */
    val imageVariationAgent = _root_ide_package_.com.github.xingray.jimeng.api.image.ImageVariationAgentApi(volcClient, pollIntervalMs, maxPollAttempts)

    // ==================== Video APIs ====================
    /** 视频3.0 Pro 文生视频 API */
    val videoProV30 = _root_ide_package_.com.github.xingray.jimeng.api.video.VideoProV30Api(volcClient, pollIntervalMs, maxPollAttempts)
    /** 视频3.0 720P API */
    val video720p = _root_ide_package_.com.github.xingray.jimeng.api.video.Video720pApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 视频3.0 1080P API */
    val video1080p = _root_ide_package_.com.github.xingray.jimeng.api.video.Video1080pApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 动作模仿 API */
    val actionMimicry = _root_ide_package_.com.github.xingray.jimeng.api.video.ActionMimicryApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** 视频重绘 API */
    val videoRemake = _root_ide_package_.com.github.xingray.jimeng.api.video.VideoRemakeApi(volcClient, pollIntervalMs, maxPollAttempts)

    // ==================== Digital Human APIs ====================
    /** 数字人快速模式 API */
    val digitalHumanQuick = _root_ide_package_.com.github.xingray.jimeng.api.digital.DigitalHumanQuickApi(volcClient, pollIntervalMs, maxPollAttempts)
    /** OmniHuman 1.5 数字人 API */
    val omniHumanV15 = _root_ide_package_.com.github.xingray.jimeng.api.digital.OmniHumanV15Api(volcClient, pollIntervalMs, maxPollAttempts)

    /** 关闭客户端，释放内部创建的HTTP客户端资源 */
    fun close() {
        ownedHttpClient?.close()
    }
}
