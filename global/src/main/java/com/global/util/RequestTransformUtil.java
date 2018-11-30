package com.global.util;


import com.global.viewmodel.AgeViewModel;
import com.global.viewmodel.DeleteAndArchivingViewModel;
import com.global.viewmodel.MyCenterSexViewModel;
import com.global.viewmodel.TaskIsReadViewModel;
import com.global.viewmodel.TaskPriorityViewModel;
import com.global.viewmodel.TaskStateViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Laughing on 2018/5/2 14:47
 * 邮箱：719240226@qq.com
 */

public class RequestTransformUtil {

    public static ArrayList<MyCenterSexViewModel> initSexData() {
        ArrayList<MyCenterSexViewModel> mSexList = new ArrayList<MyCenterSexViewModel>();
        mSexList.add(new MyCenterSexViewModel("男", "1"));
        mSexList.add(new MyCenterSexViewModel("女", "2"));
        return mSexList;
    }

    /**
     * 进行中项目操作
     *
     * @return
     */
    public static ArrayList<DeleteAndArchivingViewModel> initDeleteAndArchivingData() {
        ArrayList<DeleteAndArchivingViewModel> mDeleteAndArchiving = new ArrayList<>();
        mDeleteAndArchiving.add(new DeleteAndArchivingViewModel("编辑", "0"));
        mDeleteAndArchiving.add(new DeleteAndArchivingViewModel("删除", "1"));
        mDeleteAndArchiving.add(new DeleteAndArchivingViewModel("归档", "2"));
        return mDeleteAndArchiving;
    }

    /**
     * 已归档项目操作
     *
     * @return
     */
    public static ArrayList<DeleteAndArchivingViewModel> initDeleteData() {
        ArrayList<DeleteAndArchivingViewModel> mDeleteAndArchiving = new ArrayList<>();
        mDeleteAndArchiving.add(new DeleteAndArchivingViewModel("取消归档", "0"));
        return mDeleteAndArchiving;
    }

    /**
     * 任务-派给我的-任务优先级
     *
     * @return
     */
    public static ArrayList<TaskPriorityViewModel> initTaskPriorityData() {
        ArrayList<TaskPriorityViewModel> priorityViewModels = new ArrayList<>();
        priorityViewModels.add(new TaskPriorityViewModel("全部", "0"));
        priorityViewModels.add(new TaskPriorityViewModel("普通", "1"));
        priorityViewModels.add(new TaskPriorityViewModel("重要", "2"));
        priorityViewModels.add(new TaskPriorityViewModel("紧急", "3"));
        return priorityViewModels;
    }


    public static ArrayList<TaskStateViewModel> initTaskStateData() {
        ArrayList<TaskStateViewModel> taskStateViewModels = new ArrayList<>();
        taskStateViewModels.add(new TaskStateViewModel("全部", null));
        taskStateViewModels.add(new TaskStateViewModel("未开始", "0"));
        taskStateViewModels.add(new TaskStateViewModel("进行中", "1"));
        taskStateViewModels.add(new TaskStateViewModel("已超期", "2"));
        taskStateViewModels.add(new TaskStateViewModel("待验收", "3"));
        taskStateViewModels.add(new TaskStateViewModel("已完成", "4"));
        return taskStateViewModels;
    }

    /**
     * 任务-派给我的-任务优先级
     *
     * @return
     */
    public static ArrayList<TaskIsReadViewModel> initTaskReadData() {
        ArrayList<TaskIsReadViewModel> readViewModels = new ArrayList<>();
        readViewModels.add(new TaskIsReadViewModel("查看全部", "0"));
        readViewModels.add(new TaskIsReadViewModel("查看未阅读", "1"));
        return readViewModels;
    }


    public static List initAgeData() {
        List mAgeList = new ArrayList<>();
        mAgeList.add(new AgeViewModel("10"));
        mAgeList.add(new AgeViewModel("11"));
        mAgeList.add(new AgeViewModel("12"));
        mAgeList.add(new AgeViewModel("13"));
        mAgeList.add(new AgeViewModel("14"));
        mAgeList.add(new AgeViewModel("15"));
        mAgeList.add(new AgeViewModel("16"));
        mAgeList.add(new AgeViewModel("17"));
        mAgeList.add(new AgeViewModel("18"));
        mAgeList.add(new AgeViewModel("19"));
        mAgeList.add(new AgeViewModel("20"));
        mAgeList.add(new AgeViewModel("21"));
        mAgeList.add(new AgeViewModel("22"));
        mAgeList.add(new AgeViewModel("23"));
        mAgeList.add(new AgeViewModel("24"));
        mAgeList.add(new AgeViewModel("25"));
        mAgeList.add(new AgeViewModel("26"));
        mAgeList.add(new AgeViewModel("27"));
        mAgeList.add(new AgeViewModel("28"));
        mAgeList.add(new AgeViewModel("29"));
        mAgeList.add(new AgeViewModel("30"));
        mAgeList.add(new AgeViewModel("31"));
        mAgeList.add(new AgeViewModel("32"));
        mAgeList.add(new AgeViewModel("33"));
        mAgeList.add(new AgeViewModel("34"));
        mAgeList.add(new AgeViewModel("35"));
        mAgeList.add(new AgeViewModel("36"));
        mAgeList.add(new AgeViewModel("37"));
        mAgeList.add(new AgeViewModel("38"));
        mAgeList.add(new AgeViewModel("39"));
        mAgeList.add(new AgeViewModel("40"));
        mAgeList.add(new AgeViewModel("41"));
        mAgeList.add(new AgeViewModel("42"));
        mAgeList.add(new AgeViewModel("43"));
        mAgeList.add(new AgeViewModel("44"));
        mAgeList.add(new AgeViewModel("45"));
        mAgeList.add(new AgeViewModel("46"));
        mAgeList.add(new AgeViewModel("47"));
        mAgeList.add(new AgeViewModel("48"));
        mAgeList.add(new AgeViewModel("49"));
        mAgeList.add(new AgeViewModel("50"));
        mAgeList.add(new AgeViewModel("51"));
        mAgeList.add(new AgeViewModel("52"));
        mAgeList.add(new AgeViewModel("53"));
        mAgeList.add(new AgeViewModel("54"));
        mAgeList.add(new AgeViewModel("55"));
        mAgeList.add(new AgeViewModel("56"));
        mAgeList.add(new AgeViewModel("57"));
        mAgeList.add(new AgeViewModel("58"));
        mAgeList.add(new AgeViewModel("59"));
        mAgeList.add(new AgeViewModel("60"));
        mAgeList.add(new AgeViewModel("61"));
        mAgeList.add(new AgeViewModel("62"));
        mAgeList.add(new AgeViewModel("63"));
        mAgeList.add(new AgeViewModel("64"));
        mAgeList.add(new AgeViewModel("65"));
        mAgeList.add(new AgeViewModel("66"));
        mAgeList.add(new AgeViewModel("67"));
        mAgeList.add(new AgeViewModel("68"));
        mAgeList.add(new AgeViewModel("69"));
        mAgeList.add(new AgeViewModel("70"));
        mAgeList.add(new AgeViewModel("71"));
        mAgeList.add(new AgeViewModel("72"));
        mAgeList.add(new AgeViewModel("73"));
        mAgeList.add(new AgeViewModel("74"));
        mAgeList.add(new AgeViewModel("75"));
        mAgeList.add(new AgeViewModel("76"));
        mAgeList.add(new AgeViewModel("77"));
        mAgeList.add(new AgeViewModel("78"));
        mAgeList.add(new AgeViewModel("79"));
        mAgeList.add(new AgeViewModel("80"));
        mAgeList.add(new AgeViewModel("81"));
        mAgeList.add(new AgeViewModel("82"));
        mAgeList.add(new AgeViewModel("83"));
        mAgeList.add(new AgeViewModel("84"));
        mAgeList.add(new AgeViewModel("85"));
        mAgeList.add(new AgeViewModel("86"));
        mAgeList.add(new AgeViewModel("87"));
        mAgeList.add(new AgeViewModel("88"));
        mAgeList.add(new AgeViewModel("89"));
        mAgeList.add(new AgeViewModel("90"));
        mAgeList.add(new AgeViewModel("91"));
        mAgeList.add(new AgeViewModel("92"));
        mAgeList.add(new AgeViewModel("93"));
        mAgeList.add(new AgeViewModel("94"));
        mAgeList.add(new AgeViewModel("95"));
        mAgeList.add(new AgeViewModel("96"));
        mAgeList.add(new AgeViewModel("97"));
        mAgeList.add(new AgeViewModel("98"));
        mAgeList.add(new AgeViewModel("99"));
        return mAgeList;
    }

}