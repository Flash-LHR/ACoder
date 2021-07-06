package com.javaclimb.ACoder.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.ACoder.domain.Content;
import com.javaclimb.ACoder.service.ContentService;
import com.javaclimb.ACoder.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 内容控制类
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 添加内容
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addContent(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String contentID = request.getParameter("contentID").trim();
        String contentName = request.getParameter("contentName").trim();
        String activityID = request.getParameter("activityID").trim();
        String chapterID = request.getParameter("chapterID").trim();


        //保存到内容的对象中
        Content content = new Content();
        content.setContentID(new Integer(contentID));
        content.setContentName(contentName);
        content.setActivityID(new Integer(activityID));
        content.setChapterID(new Integer(chapterID));
        boolean flag = contentService.insert(content);
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
     * 修改内容
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateContent(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String contentID = request.getParameter("contentID").trim();
        String contentName = request.getParameter("contentName").trim();
        String activityID = request.getParameter("activityID").trim();
        String chapterID = request.getParameter("chapterID").trim();


        //保存到内容的对象中
        Content content = new Content();
        content.setId(new Integer(id));
        content.setContentID(new Integer(contentID));
        content.setContentName(contentName);
        content.setActivityID(new Integer(activityID));
        content.setChapterID(new Integer(chapterID));
        boolean flag = contentService.update(content);
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
     * 删除内容
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteContent(HttpServletRequest request){
        String id = request.getParameter("id").trim();

        boolean flag = contentService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return contentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有内容
     */
    @RequestMapping(value = "/allContent",method = RequestMethod.GET)
    public Object allContent(HttpServletRequest request){
        return contentService.allContent();
    }

    /**
     * 根据内容名字模糊查询列表
     */
    @RequestMapping(value = "/contentOfName",method = RequestMethod.GET)
    public Object contentOfName(HttpServletRequest request){
        String contentName = request.getParameter("contentName").trim();          //内容名字
        return contentService.contentOfName("%"+contentName+"%");
    }

    /**
     * 根据活动id和章节查询内容对象
     */
    @RequestMapping(value = "/chapter/activity/detail", method = RequestMethod.GET)
    public Object contentOfActivityIDAndChapterID(HttpServletRequest request) {
        String activityID = request.getParameter("activityID");
        String chapterID = request.getParameter("chapterID");
        return contentService.contentOfActivityIDAndChapterID(Integer.parseInt(activityID), Integer.parseInt(chapterID));
    }
}






















