package com.github.xingray.jimeng.model.video

import com.github.xingray.jimeng.model.common.ReqKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 即梦视频3.0 Pro 文生视频请求
 *
 * 使用即梦视频3.0 Pro模型，根据文本描述生成高质量视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_vgfm_t2v_l_v30`
 * @property prompt 视频内容描述
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 * @property aspectRatio 视频宽高比（如 "16:9"、"9:16"、"1:1"）
 */
@Serializable
data class VideoProV30T2VRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_T2V_PRO_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null,
    @SerialName("aspect_ratio")
    val aspectRatio: String? = null
)

/**
 * 即梦视频3.0 720P 文生视频请求
 *
 * 使用即梦视频3.0模型（720P分辨率），根据文本描述生成视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_t2v_v30`
 * @property prompt 视频内容描述
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 * @property aspectRatio 视频宽高比（如 "16:9"、"9:16"、"1:1"）
 */
@Serializable
data class Video720pT2VRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_T2V_720P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null,
    @SerialName("aspect_ratio")
    val aspectRatio: String? = null
)

/**
 * 即梦视频3.0 720P 首帧图生视频请求
 *
 * 使用即梦视频3.0模型（720P分辨率），根据首帧图片和文本描述生成视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_i2v_first_v30`
 * @property prompt 视频内容描述
 * @property imageUrls 首帧图片URL列表
 * @property binaryDataBase64 首帧图片Base64编码列表
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 */
@Serializable
data class Video720pI2VFirstRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_I2V_FIRST_720P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null
)

/**
 * 即梦视频3.0 720P 首尾帧图生视频请求
 *
 * 使用即梦视频3.0模型（720P分辨率），根据首帧和尾帧图片及文本描述生成视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_i2v_first_tail_v30`
 * @property prompt 视频内容描述
 * @property imageUrls 图片URL列表，包含首帧和尾帧图片
 * @property binaryDataBase64 图片Base64编码列表
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 */
@Serializable
data class Video720pI2VFirstTailRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_I2V_FIRST_TAIL_720P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null
)

/**
 * 即梦视频3.0 720P 运镜图生视频请求
 *
 * 使用即梦视频3.0模型（720P分辨率），根据运镜模板、图片和文本描述生成运镜视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_i2v_recamera_v30`
 * @property prompt 视频内容描述
 * @property templateId 运镜模板ID
 * @property cameraStrength 运镜强度
 * @property imageUrls 图片URL列表
 * @property binaryDataBase64 图片Base64编码列表
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 */
@Serializable
data class Video720pI2VRecameraRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_I2V_RECAMERA_720P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("template_id")
    val templateId: String,
    @SerialName("camera_strength")
    val cameraStrength: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null
)

/**
 * 即梦视频3.0 1080P 文生视频请求
 *
 * 使用即梦视频3.0模型（1080P分辨率），根据文本描述生成高清视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_t2v_v30_1080p`
 * @property prompt 视频内容描述
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 * @property aspectRatio 视频宽高比（如 "16:9"、"9:16"、"1:1"）
 */
@Serializable
data class Video1080pT2VRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_T2V_1080P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null,
    @SerialName("aspect_ratio")
    val aspectRatio: String? = null
)

/**
 * 即梦视频3.0 1080P 首帧图生视频请求
 *
 * 使用即梦视频3.0模型（1080P分辨率），根据首帧图片和文本描述生成高清视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_i2v_first_v30_1080`
 * @property prompt 视频内容描述
 * @property imageUrls 首帧图片URL列表
 * @property binaryDataBase64 首帧图片Base64编码列表
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 */
@Serializable
data class Video1080pI2VFirstRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_I2V_FIRST_1080P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null
)

/**
 * 即梦视频3.0 1080P 首尾帧图生视频请求
 *
 * 使用即梦视频3.0模型（1080P分辨率），根据首帧和尾帧图片及文本描述生成高清视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_i2v_first_tail_v30_1080`
 * @property prompt 视频内容描述
 * @property imageUrls 图片URL列表，包含首帧和尾帧图片
 * @property binaryDataBase64 图片Base64编码列表
 * @property seed 随机种子，-1表示随机
 * @property frames 生成视频帧数（帧数=24*n+1，n为秒数，支持5s=121帧、10s=241帧）
 */
@Serializable
data class Video1080pI2VFirstTailRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_I2V_FIRST_TAIL_1080P_V30,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("seed")
    val seed: Int? = null,
    @SerialName("frames")
    val frames: Int? = null
)

/**
 * 动作模仿1.0请求
 *
 * 根据参考动作视频和目标人物图片，生成目标人物模仿动作的视频。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_dream_actor_m1_gen_video_cv`
 * @property videoUrl 参考动作视频URL
 * @property imageUrl 目标人物图片URL
 */
@Serializable
data class ActionMimicryV10Request(
    @SerialName("req_key")
    val reqKey: String = ReqKey.ACTION_MIMICRY_V10,
    @SerialName("video_url")
    val videoUrl: String,
    @SerialName("image_url")
    val imageUrl: String
)

/**
 * 动作模仿2.0请求
 *
 * 根据参考动作视频和目标人物图片，生成目标人物模仿动作的视频。支持回调通知和结果裁剪。
 *
 * @property reqKey 请求类型标识，固定值 `jimeng_dreamactor_m20_gen_video`
 * @property videoUrl 参考动作视频URL
 * @property imageUrls 目标人物图片URL列表
 * @property binaryDataBase64 目标人物图片Base64编码列表
 * @property cutResultFirstSecondSwitch 是否裁剪结果首秒
 * @property callbackUrl 任务完成回调URL
 */
@Serializable
data class ActionMimicryV20Request(
    @SerialName("req_key")
    val reqKey: String = ReqKey.ACTION_MIMICRY_V20,
    @SerialName("video_url")
    val videoUrl: String,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    @SerialName("binary_data_base64")
    val binaryDataBase64: List<String>? = null,
    @SerialName("cut_result_first_second_switch")
    val cutResultFirstSecondSwitch: Boolean? = null,
    @SerialName("callback_url")
    val callbackUrl: String? = null
)

/**
 * 视频重绘请求
 *
 * 对原始视频进行重绘处理，可选择性地使用参考图片来引导重绘效果。
 *
 * @property reqKey 请求类型标识，固定值 `realman_video_remake_tob`
 * @property videoInput 原始视频URL
 * @property imageInput 参考图片URL
 * @property refImgSwitch 是否启用参考图片
 */
@Serializable
data class VideoRemakeRequest(
    @SerialName("req_key")
    val reqKey: String = ReqKey.VIDEO_REMAKE,
    @SerialName("video_input")
    val videoInput: String,
    @SerialName("image_input")
    val imageInput: String? = null,
    @SerialName("ref_img_switch")
    val refImgSwitch: Boolean? = null
)
