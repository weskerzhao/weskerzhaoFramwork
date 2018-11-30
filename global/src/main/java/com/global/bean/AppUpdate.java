package com.global.bean;

public class AppUpdate {
    /**
     * id : 1e5dc5d7-9854-11e7-8af8-448a5bd4aa77
     * androidVersion : 3.2.90
     * iosVersion : 1.4.5
     * createDate : 1505287271000
     * type : 2
     * content : 修改bug
     * androidDownloadUrl : https://www.yotingche.com/appStore/android/3.2.90.apk
     */
    private String id;
    private String androidVersion;  //版本名
    private String iosVersion;
    private long createDate;
    private String type;
    private String content; //更新描述
    private String androidDownloadUrl;//apk Url
    private String appVersion;  //版本号

    public AppUpdate(String id, String androidVersion, String iosVersion, long createDate, String type, String content, String androidDownloadUrl, String appVersion) {
        this.id = id;
        this.androidVersion = androidVersion;
        this.iosVersion = iosVersion;
        this.createDate = createDate;
        this.type = type;
        this.content = content;
        this.androidDownloadUrl = androidDownloadUrl;
        this.appVersion = appVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAndroidDownloadUrl() {
        return androidDownloadUrl;
    }

    public void setAndroidDownloadUrl(String androidDownloadUrl) {
        this.androidDownloadUrl = androidDownloadUrl;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
