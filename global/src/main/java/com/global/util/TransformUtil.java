package com.global.util;

import com.waterbase.utile.StrUtil;

/**
 * 字符转换
 * 作者：Laughing on 2018/5/2 12:47
 * 邮箱：719240226@qq.com
 */

public class TransformUtil {

    /**
     * 性别转成字符串
     *
     * @param nSex Num
     * @return String
     */
    public static String sexTransformStr(int nSex) {
        switch (nSex) {
            case 0:
                return "未知的性别";
            case 1:
                return "男";
            case 2:
                return "女";
            default:
                return "";
        }
    }

    /**
     * int型日期转成yyyy-MM-dd
     *
     * @param timestampString
     * @return
     */
    public static String TimeStampDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(timestamp));
        return date;
    }


    /**
     * 性别转成字符串
     *
     * @param nSex String
     * @return Num
     */
    public static String sexTransformStr(String nSex) {
        if (StrUtil.isEmpty(nSex))
            return "未知";
        switch (nSex) {
            case "0":
                return "未知的性别";
            case "1":
                return "男";
            case "2":
                return "女";
            default:
                return "";
        }
    }

    /**
     * 上传数据到服务器时：字符串转成性别Num
     *
     * @param nSex
     * @return
     */
    public static String sexStrTransformNum(String nSex) {
        switch (nSex) {
            case "男":
                return "1";
            case "女":
                return "2";
            default:
                return "0";
        }
    }


    /**
     * 任务的优先级
     * 上传数据到服务器时：字符串转成Num
     * "全部", "0"
     * "普通", "1"
     * "重要", "2"
     * "紧急", "3"
     *
     * @param priority
     * @return
     */
    public static String priorityStrTransformNum(String priority) {
        switch (priority) {
            case "全部":
                return "0";
            case "普通":
                return "1";
            case "重要":
                return "2";
            case "紧急":
                return "3";
            default:
                return "0";
        }
    }

    /**
     * 任务的优先级
     * 显示数据时：Num转成字符串
     * "全部", "0"
     * "普通", "1"
     * "重要", "2"
     * "紧急", "3"
     *
     * @param priority
     * @return
     */
    public static String priorityNumTransformStr(String priority) {
        switch (priority) {
            case "0":
                return "全部";
            case "1":
                return "普通";
            case "2":
                return "重要";
            case "3":
                return "紧急";
            default:
                return "0";
        }
    }

    /**
     * 任务的状态
     * 上传数据到服务器时：字符串转成Num
     * "全部", "0"
     * "未开始", "1"
     * "进行中", "2"
     * "已超期", "3"
     * "待验收", "4"
     * "已完成", "5"
     *
     * @param priority
     * @return
     */
    public static String stateStrTransformNum(String priority) {
        switch (priority) {
            case "全部":
                return "0";
            case "未开始":
                return "1";
            case "进行中":
                return "2";
            case "已超期":
                return "3";
            case "待验收":
                return "4";
            case "已完成":
                return "5";
            default:
                return "0";
        }
    }

    /**
     * 任务的状态
     * 显示数据时：Num转成字符串
     * "全部", "0"
     * "未开始", "1"
     * "进行中", "2"
     * "已超期", "3"
     * "待验收", "4"
     * "已完成", "5"
     *
     * @param priority
     * @return
     */
    public static String stateNumTransformStr(String priority) {
        switch (priority) {
            case "0":
                return "全部";
            case "1":
                return "未开始";
            case "2":
                return "进行中";
            case "3":
                return "已超期";
            case "4":
                return "待验收";
            case "5":
                return "已完成";
            default:
                return "0";
        }
    }

    /**
     * 人员id转姓名
     *
     * @param userId Long
     * @return String
     */
    public static String userIdToString(Long userId) {
        String user = String.valueOf(userId);
        switch (user) {
            case "20L":
                return "张三";
            default:
                return "";
        }
    }

    /**
     * 审批状态0:待审批1:驳回2审批通过
     *
     * @param approveStatus String
     * @return String
     */
    public static String ApprovalStatusToShowString(String approveStatus) {
        switch (approveStatus) {
            case "0":
                return "待审批";
            case "1":
                return "驳回";
            case "2":
                return "审批通过";
            default:
                return "未知状态";
        }
    }


    /**
     * working 模块使用
     * 转换数据（去掉字符串最后的 "米" 字）
     *
     * @param range 考勤范围字符串
     * @return
     */
    public static int transform(String range) {
        int range2 = 500;
        switch (range) {
            case "300米":
                range2 = 300;
                break;
            case "500米":
                range2 = 500;
                break;
            case "1000米":
                range2 = 1000;
                break;
            case "2000米":
                range2 = 2000;
                break;

            default:

                break;
        }
        return range2;

    }

    /**
     * 打卡类型转为文字
     *
     * @param type
     * @return
     */
    public static String transformPunchType2String(String type) {
        String typeStr = "";
        switch (type) {
            case "0":
                typeStr = "上班打卡";
                break;
            case "1":
                typeStr = "下班打卡";
                break;
            case "2":
                typeStr = "外勤打卡";
                break;
            default:
                break;
        }
        return typeStr;

    }


}
