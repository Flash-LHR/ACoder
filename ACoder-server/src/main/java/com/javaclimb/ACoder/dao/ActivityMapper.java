package com.javaclimb.ACoder.dao;

import com.javaclimb.ACoder.domain.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活动Dao
 */
@Repository
public interface ActivityMapper {
    /**
     *增加
     */
    public int insert(Activity activity);
    /**
     *修改
     */
    public int update(Activity activity);

    /**
     * 删除
     */
    public int delete(Integer id);

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
















