package com.waterbase.bean;

/**
 * 获取app版本名与版本号
 * 作者：Laughing on 2018/9/5 10:20
 * 邮箱：719240226@qq.com
 */
public class AppVersion {
    private int versionCode;
    private String versionName;

    public AppVersion(int versionCode, String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
