package com.github.xingray.jimeng.model.common

/**
 * 即梦AI服务请求类型标识（req_key）常量
 */
object ReqKey {
    // ==================== 图像生成 ====================

    /** 文生图 v3.0 */
    const val TEXT_TO_IMAGE_V30 = "jimeng_t2i_v30"
    /** 文生图 v3.1 */
    const val TEXT_TO_IMAGE_V31 = "jimeng_t2i_v31"
    /** 文生图 v4.0 */
    const val TEXT_TO_IMAGE_V40 = "jimeng_t2i_v40"
    /** 智能参考图生图 v3.1 */
    const val IMAGE_TO_IMAGE_SMART_REF_V31 = "jimeng_high_aes_general_v31"

    // ==================== 图像编辑 ====================

    /** 局部重绘（Inpainting） */
    const val INPAINTING = "jimeng_image2image_dream_inpaint"
    /** 超分辨率 */
    const val SUPER_RESOLUTION = "jimeng_i2i_seed3_tilesr_cvtob"
    /** 材质提取（POD） */
    const val MATERIAL_EXTRACTION_POD = "i2i_material_extraction"
    /** 材质提取（商品） */
    const val MATERIAL_EXTRACTION_PRODUCT = "jimeng_i2i_extract_tiled_images"
    /** 营销商品图 v3.0 */
    const val MARKETING_PRODUCT_IMAGE_V30 = "jimeng_ecom_product_img_v30"
    /** 证件照/形象照 */
    const val PORTRAIT_PHOTO = "i2i_single_faceswap_jimeng"
    /** 动漫风格化 */
    const val ANIME_STYLE = "img2img_anime_jimeng"

    // ==================== 视频生成 - 3.0 Pro ====================

    /** 文生视频 3.0 Pro */
    const val VIDEO_T2V_PRO_V30 = "jimeng_vgfm_t2v_l_v30"

    // ==================== 视频生成 - 3.0 720P ====================

    /** 文生视频 3.0 720P */
    const val VIDEO_T2V_720P_V30 = "jimeng_t2v_v30"
    /** 图生视频（首帧）3.0 720P */
    const val VIDEO_I2V_FIRST_720P_V30 = "jimeng_i2v_first_v30"
    /** 图生视频（首尾帧）3.0 720P */
    const val VIDEO_I2V_FIRST_TAIL_720P_V30 = "jimeng_i2v_first_tail_v30"
    /** 图生视频（运镜）3.0 720P */
    const val VIDEO_I2V_RECAMERA_720P_V30 = "jimeng_i2v_recamera_v30"

    // ==================== 视频生成 - 3.0 1080P ====================

    /** 文生视频 3.0 1080P */
    const val VIDEO_T2V_1080P_V30 = "jimeng_t2v_v30_1080p"
    /** 图生视频（首帧）3.0 1080P */
    const val VIDEO_I2V_FIRST_1080P_V30 = "jimeng_i2v_first_v30_1080"
    /** 图生视频（首尾帧）3.0 1080P */
    const val VIDEO_I2V_FIRST_TAIL_1080P_V30 = "jimeng_i2v_first_tail_v30_1080"

    // ==================== 动作模仿 ====================

    /** 动作模仿 v1.0 */
    const val ACTION_MIMICRY_V10 = "jimeng_dream_actor_m1_gen_video_cv"
    /** 动作模仿 v2.0 */
    const val ACTION_MIMICRY_V20 = "jimeng_dreamactor_m20_gen_video"

    // ==================== 数字人 - 快捷模式 ====================

    /** 数字人快捷模式 - 形象识别 */
    const val DIGITAL_HUMAN_QUICK_RECOGNIZE = "jimeng_realman_avatar_picture_create_role_omni"
    /** 数字人快捷模式 - 视频生成 */
    const val DIGITAL_HUMAN_QUICK_VIDEO = "jimeng_realman_avatar_picture_omni_v2"

    // ==================== 数字人 - OmniHuman 1.5 ====================

    /** OmniHuman 1.5 - 形象识别 */
    const val OMNIHUMAN_V15_RECOGNIZE = "jimeng_realman_avatar_picture_create_role_omni_v15"
    /** OmniHuman 1.5 - 主体检测 */
    const val OMNIHUMAN_V15_DETECTION = "jimeng_realman_avatar_object_detection"
    /** OmniHuman 1.5 - 视频生成 */
    const val OMNIHUMAN_V15_VIDEO = "jimeng_realman_avatar_picture_omni_v15"

    // ==================== 视频翻拍 ====================

    /** 视频翻拍 */
    const val VIDEO_REMAKE = "realman_video_remake_tob"

    // ==================== 图像变体智能体 ====================

    /** 广告图智能体 */
    const val AD_IMAGE_AGENT = "i2i_ad_agent"
    /** 短剧封面智能体 */
    const val NOVEL_COVER_AGENT = "i2i_short_play_cover_agent"
    /** 图像变体智能体 */
    const val IMAGE_VARIATION_AGENT = "i2i_ads_agent"
}
