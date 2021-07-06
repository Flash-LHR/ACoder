package com.javaclimb.ACoder.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.ACoder.domain.Chapter;
import com.javaclimb.ACoder.service.ChapterService;
import com.javaclimb.ACoder.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 章节控制类
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 添加章节
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addChapter(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String chapterID = request.getParameter("chapterID").trim();
        String chapterName = request.getParameter("chapterName").trim();
        String activityID = request.getParameter("activityID").trim();

        //保存到章节的对象中
        Chapter chapter = new Chapter();
        chapter.setChapterID(new Integer(chapterID));
        chapter.setChapterName(chapterName);
        chapter.setActivityID(new Integer(activityID));
        boolean flag = chapterService.insert(chapter);
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
     * 修改章节
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateChapter(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String chapterID = request.getParameter("chapterID").trim();
        String chapterName = request.getParameter("chapterName").trim();
        String activityID = request.getParameter("activityID").trim();
        //保存到章节的对象中
        Chapter chapter = new Chapter();
        chapter.setId(new Integer(id));
        chapter.setChapterID(new Integer(chapterID));
        chapter.setChapterName(chapterName);
        chapter.setActivityID(new Integer(activityID));
        boolean flag = chapterService.update(chapter);
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
     * 删除章节
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteChapter(HttpServletRequest request){
        String id = request.getParameter("id").trim();

        boolean flag = chapterService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return chapterService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有章节
     */
    @RequestMapping(value = "/allChapter",method = RequestMethod.GET)
    public Object allChapter(HttpServletRequest request){
        return chapterService.allChapter();
    }

    /**
     * 根据章节名字模糊查询列表
     */
    @RequestMapping(value = "/chapterOfName",method = RequestMethod.GET)
    public Object chapterOfName(HttpServletRequest request){
        String chapterName = request.getParameter("chapterName").trim();          //章节名字
        return chapterService.chapterOfName("%"+chapterName+"%");
    }

    /**
     * 根据活动id查询章节对象
     */
    @RequestMapping(value = "/activity/detail", method = RequestMethod.GET)
    public Object chapterOfActivityID(HttpServletRequest request) {
        String activityID = request.getParameter("activityID");
        return chapterService.chapterOfActivityID(Integer.parseInt(activityID));
    }
}






















