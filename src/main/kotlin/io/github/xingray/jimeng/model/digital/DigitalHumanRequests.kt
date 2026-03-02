package io.github.xingray.jimeng.model.digital

import io.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 数字人快速模式 - 主体识别请求（步骤1）
 *
 * 数字人快速模式的第一步，对输入的人物图片进行主体识别。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_realman_avatar_picture_create_role_omni`
 * @property imageUrl 人物图片URL
 */
@Serializable
data class DigitalHumanQuickRecognizeRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.DIGITAL_HUMAN_QUICK_RECOGNIZE,
    @SerialName("image_url")
    val imageUrl: String
)

/**
 * 数字人快速模式 - 视频生成请求（步骤2）
 *
 * 数字人快速模式的第二步，根据人物图片和驱动音频生成数字人视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_realman_avatar_picture_omni_v2`
 * @property imageUrl 人物图片URL
 * @property audioUrl 驱动音频URL
 */
@Serializable
data class DigitalHumanQuickVideoRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.DIGITAL_HUMAN_QUICK_VIDEO,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("audio_url")
    val audioUrl: String
)

/**
 * OmniHuman 1.5 - 主体识别请求（步骤1）
 *
 * OmniHuman 1.5的第一步，对输入的人物图片进行主体识别。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_realman_avatar_picture_create_role_omni_v15`
 * @property imageUrl 人物图片URL
 */
@Serializable
data class OmniHumanV15RecognizeRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.OMNIHUMAN_V15_RECOGNIZE,
    @SerialName("image_url")
    val imageUrl: String
)

/**
 * OmniHuman 1.5 - 主体检测请求（步骤2，同步接口）
 *
 * OmniHuman 1.5的第二步，对输入的人物图片进行主体检测。此接口为同步接口，直接返回检测结果。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_realman_avatar_object_detection`
 * @property imageUrl 人物图片URL
 */
@Serializable
data class OmniHumanV15DetectionRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.OMNIHUMAN_V15_DETECTION,
    @SerialName("image_url")
    val imageUrl: String
)

/**
 * OmniHuman 1.5 - 视频生成请求（步骤3）
 *
 * OmniHuman 1.5的第三步，根据人物图片、驱动音频和主体蒙版生成数字人视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_realman_avatar_picture_omni_v15`
 * @property imageUrl 人物图片URL
 * @property audioUrl 驱动音频URL
 * @property maskUrl 主体蒙版URL列表（来自检测步骤）
 * @property seed 随机种子
 * @property prompt 提示词（可选）
 * @property outputResolution 输出分辨率（如 720、1080）
 * @property peFastMode 是否启用快速模式
 */
@Serializable
data class OmniHumanV15VideoRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.OMNIHUMAN_V15_VIDEO,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("audio_url")
    val audioUrl: String,
    @SerialName("mask_url")
    val maskUrl: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("prompt")
    val prompt: String? = null,
    @SerialName("output_resolution")
    val outputResolution: Int? = null,
    @SerialName("pe_fast_mode")
    val peFastMode: Boolean? = null
)
