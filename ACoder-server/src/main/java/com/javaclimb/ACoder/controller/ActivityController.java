package com.javaclimb.ACoder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javaclimb.ACoder.domain.Activity;
import com.javaclimb.ACoder.service.ActivityService;
import com.javaclimb.ACoder.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 活动控制类
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 添加活动
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addActivity(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String activityID = request.getParameter("activityID").trim();
        String activityName = request.getParameter("activityName").trim();

        //保存到活动的对象中
        Activity activity = new Activity();
        activity.setActivityID(new Integer(activityID));
        activity.setActivityName(activityName);
        boolean flag = activityService.insert(activity);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改活动
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateActivity(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String activityID = request.getParameter("activityID").trim();
        String activityName = request.getParameter("activityName").trim();
        //保存到活动的对象中
        Activity activity = new Activity();
        activity.setId(new Integer(id));
        activity.setActivityID(new Integer(activityID));
        activity.setActivityName(activityName);
        boolean flag = activityService.update(activity);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }


    /**
     * 删除活动
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteActivity(HttpServletRequest request){
        String id = request.getParameter("id").trim();

        boolean flag = activityService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return activityService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有活动
     */
    @RequestMapping(value = "/allActivity",method = RequestMethod.GET)
    public Object allActivity(HttpServletRequest request){
        return activityService.allActivity();
    }

    /**
     * 根据活动名字模糊查询列表
     */
    @RequestMapping(value = "/activityOfName",method = RequestMethod.GET)
    public Object activityOfName(HttpServletRequest request){
        System.out.println("---------------------------------------\n");
        String activityName = request.getParameter("activityName").trim();          //活动名字
        return activityService.activityOfName("%"+activityName+"%");
    }
}






















