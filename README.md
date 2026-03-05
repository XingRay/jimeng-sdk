# Jimeng SDK

[中文版本](#即梦-ai-sdk)

A Kotlin SDK for Jimeng AI (即梦 AI), built on the Volcengine Visual Intelligence platform. Provides convenient access to image generation, video generation, digital human, and other AI capabilities.

## Features

- Pure Kotlin with coroutine support
- Lightweight and efficient, powered by Ktor + OkHttp
- Type-safe serialization with Kotlinx Serialization
- Automatic Volcengine HMAC-SHA256 request signing
- Built-in async task polling — submit and get results in one call
- Independent API classes for each service

## Requirements

- JDK 17+
- Kotlin 2.1+

## Installation

### Gradle (Kotlin DSL)

First, add JitPack repository in your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then add the dependency in your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.xingray:jimeng-sdk:0.0.1")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'io.github.xingray:jimeng-sdk:0.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.xingray</groupId>
    <artifactId>jimeng-sdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Quick Start

### 1. Get Credentials

Obtain your Access Key and Secret Key from the [Volcengine Console](https://console.volcengine.com/).

### 2. Basic Usage

```kotlin
import io.github.xingray.jimeng.JimengClient
import io.github.xingray.jimeng.auth.Credential
import io.github.xingray.jimeng.model.image.TextToImageV30Request

val client = JimengClient()
val credential = Credential(accessKey = "your-ak", secretKey = "your-sk")

// Text-to-Image 3.0 — submit and wait for result
val resp = client.textToImageV30.submitAndWaitForUrl(
    credential,
    TextToImageV30Request(prompt = "An orange cat sitting on a windowsill", width = 512, height = 512)
)

if (resp.isSuccess) {
    val urls = resp.data?.imageUrls
    println("Generated images: $urls")
}

// Close when done
client.close()
```

### 3. Step-by-Step (submit + query)

```kotlin
// Submit task
val submitResp = client.textToImageV30.submit(credential, request)
val taskId = submitResp.data!!.taskId

// Query result (returns image URLs)
val result = client.textToImageV30.queryUrl(credential, taskId)

// Or query result (returns Base64-encoded images)
val result = client.textToImageV30.queryBase64(credential, taskId)
```

### 4. Video Generation

```kotlin
import io.github.xingray.jimeng.model.video.VideoProV30T2VRequest

val resp = client.videoProV30.submitAndWait(
    credential,
    VideoProV30T2VRequest(prompt = "Slow-motion waves crashing on a beach", frames = 121)
)

println("Video URL: ${resp.data?.videoUrl}")
```

### 5. Digital Human

```kotlin
import io.github.xingray.jimeng.model.digital.DigitalHumanQuickRecognizeRequest

// Step 1: Subject recognition
val recognizeResp = client.digitalHumanQuick.submitAndWaitRecognize(
    credential,
    DigitalHumanQuickRecognizeRequest(imageUrl = "https://example.com/portrait.jpg")
)
```

### 6. Custom Configuration

```kotlin
val client = JimengClient(
    region = "cn-north-1",       // Service region
    pollIntervalMs = 5000L,      // Polling interval in milliseconds
    maxPollAttempts = 100,       // Maximum polling attempts
    httpClient = customClient    // Custom Ktor HttpClient
)
```

## Supported APIs

### Image Generation (14)

| API | Property | Description |
|-----|----------|-------------|
| Text-to-Image 3.0 | `textToImageV30` | Generate images from text prompts |
| Text-to-Image 3.1 | `textToImageV31` | Upgraded version of 3.0 |
| Image Generation 4.0 | `imageGenerationV40` | Latest generation model |
| Smart Reference I2I | `imageToImageSmartRef` | Generate images with reference |
| Inpainting | `inpainting` | Local image editing |
| Super Resolution | `superResolution` | Image upscaling and enhancement |
| Material Extraction (POD) | `materialExtractionPod` | Product subject extraction |
| Material Extraction (Product) | `materialExtractionProduct` | Product material extraction |
| Marketing Product Image | `marketingProductImage` | Marketing product image generation |
| Portrait Photo | `portraitPhoto` | ID photo / portrait generation |
| Anime Style | `animeStyle` | Anime style conversion |
| Ad Image Agent | `adImageAgent` | Advertisement image generation |
| Novel Cover Agent | `novelCoverAgent` | Novel / short drama cover generation |
| Image Variation Agent | `imageVariationAgent` | Image variant generation |

### Video Generation (5)

| API | Property | Description |
|-----|----------|-------------|
| Video 3.0 Pro | `videoProV30` | Pro text-to-video |
| Video 720P | `video720p` | Text / image to video at 720P |
| Video 1080P | `video1080p` | Text / image to video at 1080P |
| Action Mimicry | `actionMimicry` | Motion-driven video generation |
| Video Remake | `videoRemake` | Video style remake |

### Digital Human (2)

| API | Property | Description |
|-----|----------|-------------|
| Digital Human Quick | `digitalHumanQuick` | Quick digital human video generation |
| OmniHuman 1.5 | `omniHumanV15` | High-quality digital human video generation |

## Method Conventions

### Image APIs

Each image API provides 5 methods:

| Method | Description |
|--------|-------------|
| `submit(credential, request)` | Submit task, returns taskId |
| `queryUrl(credential, taskId)` | Query result, returns image URLs |
| `queryBase64(credential, taskId)` | Query result, returns Base64-encoded data |
| `submitAndWaitForUrl(credential, request)` | Submit and poll, returns image URLs |
| `submitAndWaitForBase64(credential, request)` | Submit and poll, returns Base64-encoded data |

### Video APIs

Each video API provides 3 methods:

| Method | Description |
|--------|-------------|
| `submit(credential, request)` | Submit task |
| `query(credential, taskId)` | Query result |
| `submitAndWait(credential, request)` | Submit and poll until complete |

## Tech Stack

| Component | Version |
|-----------|---------|
| Kotlin | 2.1.10 |
| Ktor | 3.1.1 |
| Kotlinx Serialization | 1.8.0 |
| OkHttp | (bundled with Ktor) |
| JUnit 5 | (testing) |

## License

[Apache License 2.0](LICENSE)

---

# 即梦 AI SDK

即梦 AI (Jimeng AI) Kotlin SDK，基于火山引擎视觉智能开放平台，提供图像生成、视频生成、数字人等 AI 能力的便捷接入。

## 特性

- 纯 Kotlin 实现，支持协程
- 基于 Ktor + OkHttp，轻量高效
- Kotlinx Serialization 序列化，类型安全
- 火山引擎 HMAC-SHA256 签名自动处理
- 异步任务自动轮询，submit 一步到位
- 每个 API 独立封装，按需使用

## 环境要求

- JDK 17+
- Kotlin 2.1+

## 安装

### Gradle (Kotlin DSL)

首先在项目根目录的 `settings.gradle.kts` 中添加 JitPack 仓库：

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

然后在模块的 `build.gradle.kts` 中添加依赖：

```kotlin
dependencies {
    implementation("io.github.xingray:jimeng-sdk:0.0.1")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'io.github.xingray:jimeng-sdk:0.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.xingray</groupId>
    <artifactId>jimeng-sdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 快速开始

### 1. 获取凭证

在 [火山引擎控制台](https://console.volcengine.com/) 获取 Access Key 和 Secret Key。

### 2. 基本用法

```kotlin
import io.github.xingray.jimeng.JimengClient
import io.github.xingray.jimeng.auth.Credential
import io.github.xingray.jimeng.model.image.TextToImageV30Request

val client = JimengClient()
val credential = Credential(accessKey = "your-ak", secretKey = "your-sk")

// 文生图 3.0 - 提交并等待结果
val resp = client.textToImageV30.submitAndWaitForUrl(
    credential,
    TextToImageV30Request(prompt = "一只橘色的猫咪坐在窗台上", width = 512, height = 512)
)

if (resp.isSuccess) {
    val urls = resp.data?.imageUrls
    println("生成图片: $urls")
}

// 用完关闭
client.close()
```

### 3. 分步调用（submit + query）

```kotlin
// 提交任务
val submitResp = client.textToImageV30.submit(credential, request)
val taskId = submitResp.data!!.taskId

// 查询结果（返回图片 URL）
val result = client.textToImageV30.queryUrl(credential, taskId)

// 或查询结果（返回 Base64）
val result = client.textToImageV30.queryBase64(credential, taskId)
```

### 4. 视频生成

```kotlin
import io.github.xingray.jimeng.model.video.VideoProV30T2VRequest

val resp = client.videoProV30.submitAndWait(
    credential,
    VideoProV30T2VRequest(prompt = "海浪拍打沙滩的慢镜头", frames = 121)
)

println("视频链接: ${resp.data?.videoUrl}")
```

### 5. 数字人

```kotlin
import io.github.xingray.jimeng.model.digital.DigitalHumanQuickRecognizeRequest

// 步骤1: 主体识别
val recognizeResp = client.digitalHumanQuick.submitAndWaitRecognize(
    credential,
    DigitalHumanQuickRecognizeRequest(imageUrl = "https://example.com/portrait.jpg")
)
```

### 6. 自定义配置

```kotlin
val client = JimengClient(
    region = "cn-north-1",       // 服务区域
    pollIntervalMs = 5000L,      // 轮询间隔（毫秒）
    maxPollAttempts = 100,       // 最大轮询次数
    httpClient = customClient    // 自定义 Ktor HttpClient
)
```

## 支持的 API

### 图像生成（14 个）

| API | 属性名 | 说明 |
|-----|--------|------|
| 文生图 3.0 | `textToImageV30` | 通过文本描述生成图像 |
| 文生图 3.1 | `textToImageV31` | 3.0 版本升级迭代 |
| 图像生成 4.0 | `imageGenerationV40` | 最新一代图像生成 |
| 智能参考图生图 | `imageToImageSmartRef` | 基于参考图生成 |
| 局部重绘 | `inpainting` | 图像局部编辑 |
| 智能高清 | `superResolution` | 图像超分辨率增强 |
| 素材抽取-POD | `materialExtractionPod` | 商品主体抽取 |
| 素材抽取-商品图 | `materialExtractionProduct` | 商品图素材提取 |
| AI营销商品图 | `marketingProductImage` | 营销场景商品图生成 |
| 写真照片 | `portraitPhoto` | 证件照/形象照生成 |
| 动漫风格 | `animeStyle` | 图像动漫风格化 |
| 广告配图智能体 | `adImageAgent` | 广告配图生成 |
| 小说封面智能体 | `novelCoverAgent` | 小说/短剧封面生成 |
| 图片变体智能体 | `imageVariationAgent` | 图片变体生成 |

### 视频生成（5 个）

| API | 属性名 | 说明 |
|-----|--------|------|
| 视频 3.0 Pro | `videoProV30` | 文生视频 Pro 版 |
| 视频 720P | `video720p` | 文生/图生视频 720P |
| 视频 1080P | `video1080p` | 文生/图生视频 1080P |
| 动作模仿 | `actionMimicry` | 动作驱动视频生成 |
| 视频翻拍 | `videoRemake` | 视频风格重绘 |

### 数字人（2 个）

| API | 属性名 | 说明 |
|-----|--------|------|
| 数字人快捷模式 | `digitalHumanQuick` | 快速数字人视频生成 |
| OmniHuman 1.5 | `omniHumanV15` | 高质量数字人视频生成 |

## 方法约定

### 图像 API

每个图像 API 提供 5 个方法：

| 方法 | 说明 |
|------|------|
| `submit(credential, request)` | 提交任务，返回 taskId |
| `queryUrl(credential, taskId)` | 查询结果，返回图片 URL |
| `queryBase64(credential, taskId)` | 查询结果，返回 Base64 编码 |
| `submitAndWaitForUrl(credential, request)` | 提交并轮询，返回图片 URL |
| `submitAndWaitForBase64(credential, request)` | 提交并轮询，返回 Base64 编码 |

### 视频 API

每个视频 API 提供 3 个方法：

| 方法 | 说明 |
|------|------|
| `submit(credential, request)` | 提交任务 |
| `query(credential, taskId)` | 查询结果 |
| `submitAndWait(credential, request)` | 提交并轮询 |

## 技术栈

| 组件 | 版本 |
|------|------|
| Kotlin | 2.1.10 |
| Ktor | 3.1.1 |
| Kotlinx Serialization | 1.8.0 |
| OkHttp | (Ktor 内置) |
| JUnit 5 | (测试) |

## 许可证

[Apache License 2.0](LICENSE)
