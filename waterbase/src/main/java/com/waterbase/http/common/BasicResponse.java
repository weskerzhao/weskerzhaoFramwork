package com.waterbase.http.common;

/**
 * 服务器返回数据的基础类
 * Created by Water on 2018/3/29.
 */
public class BasicResponse<T> {
//    /**
//     * success : false
//     * status : 4020
//     * error : 0
//     * message : 用户不存在!
//     * resultMap : null
//     */
//    private int status;
//    private boolean success;
//    private int error;
//    private String message;
//    private T resultMap;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public int getError() {
//        return error;
//    }
//
//    public void setError(int error) {
//        this.error = error;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getResultMap() {
//        return resultMap;
//    }
//
//    public void setResultMap(T resultMap) {
//        this.resultMap = resultMap;
//    }


    private int code;       //返回 0 表示成功
    private String msg;     //描述信息
    private T data;       //json数据

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
