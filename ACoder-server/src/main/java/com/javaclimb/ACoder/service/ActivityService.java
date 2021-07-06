package com.javaclimb.ACoder.service;

import com.javaclimb.ACoder.domain.Activity;

import java.util.List;

/**
 * 活动service接口
 */
public interface ActivityService {
    /**
     *增加
     */
    public boolean insert(Activity activity);
    /**
     *修改
     */
    public boolean update(Activity activity);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Activity selectByPrimaryKey(Integer id);

    /**
     * 查询所有活动
     */
    public List<Activity> allActivity();

    /**
     * 根据活动名字模糊查询列表
     */
    public List<Activity> activityOfName(String activityName);
}
