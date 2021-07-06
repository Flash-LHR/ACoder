package com.javaclimb.ACoder.service.impl;

import com.javaclimb.ACoder.dao.ActivityMapper;
import com.javaclimb.ACoder.domain.Activity;
import com.javaclimb.ACoder.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动service实现类
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 增加
     *
     * @param activity
     */
    @Override
    public boolean insert(Activity activity) { return activityMapper.insert(activity)>0; }
    /**
     * 修改
     *
     * @param activity
     */
    @Override
    public boolean update(Activity activity) { return activityMapper.update(activity)>0; }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {

        return activityMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Activity selectByPrimaryKey(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有活动
     */
    @Override
    public List<Activity> allActivity() {
        return activityMapper.allActivity();
    }

    /**
     * 根据活动名字模糊查询列表
     *
     * @param activityName
     */
    @Override
    public List<Activity> activityOfName(String activityName) {
        return activityMapper.activityOfName(activityName);
    }
}
