package com.waterbase.global;

/**
 * app配置类
 * 作者：Laughing on 2018/10/13 14:06
 * 邮箱：719240226@qq.com
 * 名称：AbAppConfig.java
 * 描述：初始设置类.
 *
 * @author LiuQi
 */
public class AppConfig {


    /**
     * UI设计的基准宽度.
     */

    public static int UI_WIDTH = 1242;

    /**
     * UI设计的基准高度.
     */
    public static int UI_HEIGHT = 2208;

    /**
     * UI设计的密度.
     */
    public static int UI_DENSITY = 1;

    /**
     * 默认 SharePreferences文件名.
     */
    public static String SHARED_PATH = "app_share";

    /**
     * 默认下载文件地址.
     */
    public static String DOWNLOAD_ROOT_DIR = "download";

    /**
     * 默认下载图片文件地址.
     */
    public static String DOWNLOAD_IMAGE_DIR = "images";

    /**
     * 默认下载文件地址.
     */
    public static String DOWNLOAD_FILE_DIR = "files";

    /**
     * APP缓存目录.
     */
    public static String CACHE_DIR = "cache";

    /**
     * DB目录.
     */
    public static String DB_DIR = "ycw_db";

    /**
     * 默认磁盘缓存超时时间设置，毫秒.
     */
    public static long DISK_CACHE_EXPIRES_TIME = 60 * 1000 * 1000;

    /**
     * 内存缓存大小  单位10M.
     */
    public static int MAX_CACHE_SIZE_INBYTES = 10 * 1024 * 1024;

    /**
     * 磁盘缓存大小  单位10M.
     */
    public static int MAX_DISK_USAGE_INBYTES = 10 * 1024 * 1024;

    /**
     * 主页面初始化程序需要用到的核心权限
     */
    public static boolean PhoneInfo = false;//手机信息权限
    public static boolean LOCATION = false;//定位权限
    public static boolean Write = false;//写SD卡权限
    public static boolean Read = false;//读SD卡权限
    public static boolean CAMERA = false;//拍照权限
    public static boolean PHONE_STATE = false;//手机系统信息
    //头像上传时的 key
    public static String headName = "ycw_head_pic_photo.jpg";

    public static final String isFirstInGuide = "is_guide_showed";//是否第一次进入 A_Guide页面

    public static final String PREF_USER_TOKEN = "USER_INFO";// 保存用户账户信息
    public static final String PREF_PARIKING_LOT_ID = "PREF_PARIKING_LOT_ID";// 保存用户对应停车场Id
    public static final String PREF_USER_NAME = "PREF_USER_NAME";// 保存用户名
    public static final String PREF_USER_AUTHORITY = "PREF_USER_AUTHORITY";// 保存用户权限

    //从登陆页面传递到Home页面的数据
    public static final String EXTRA_PARKING_LOT_DETAIL = "PARKING_LOT_DETAIL";
    //帐号管理
    public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    //搜索
    public static final String Extra_ParkingLotId = "ParkingLotId";

    public static boolean isShowUpdateDialog = true;//是否展示版本更新 对话框
}
