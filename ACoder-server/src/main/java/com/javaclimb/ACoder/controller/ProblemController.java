package com.javaclimb.ACoder.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.ACoder.domain.Problem;
import com.javaclimb.ACoder.service.ProblemService;
import com.javaclimb.ACoder.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 题目控制类
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /**
     * 添加题目
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addProblem(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String problemID = request.getParameter("problemID").trim();
        String problemName = request.getParameter("problemName").trim();
        String problemLink = request.getParameter("problemLink").trim();
        String problemStatus = request.getParameter("problemStatus").trim();
        String activityID = request.getParameter("activityID").trim();
        String chapterID = request.getParameter("chapterID").trim();
        String contentID = request.getParameter("contentID").trim();

        //保存到题目的对象中
        Problem problem = new Problem();
        problem.setProblemID(new Integer(problemID));
        problem.setProblemName(problemName);
        problem.setProblemLink(problemLink);
        problem.setProblemStatus(problemStatus);
        problem.setActivityID(new Integer(activityID));
        problem.setChapterID(new Integer(chapterID));
        problem.setContentID(new Integer(contentID));
        boolean flag = problemService.insert(problem);
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
     * 修改题目
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateProblem(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String problemID = request.getParameter("problemID").trim();
        String problemName = request.getParameter("problemName").trim();
        String problemLink = request.getParameter("problemLink").trim();
        String problemStatus = request.getParameter("problemStatus").trim();
        String activityID = request.getParameter("activityID").trim();
        String chapterID = request.getParameter("chapterID").trim();
        String contentID = request.getParameter("contentID").trim();

        //保存到题目的对象中
        Problem problem = new Problem();
        problem.setId(new Integer(id));
        problem.setProblemID(new Integer(problemID));
        problem.setProblemName(problemName);
        problem.setProblemLink(problemLink);
        problem.setProblemStatus(problemStatus);
        problem.setActivityID(new Integer(activityID));
        problem.setChapterID(new Integer(chapterID));
        problem.setContentID(new Integer(contentID));
        boolean flag = problemService.update(problem);
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
     * 删除题目
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteProblem(HttpServletRequest request){
        String id = request.getParameter("id").trim();

        boolean flag = problemService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return problemService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有题目
     */
    @RequestMapping(value = "/allProblem",method = RequestMethod.GET)
    public Object allProblem(HttpServletRequest request){
        return problemService.allProblem();
    }

    /**
     * 根据题目名字模糊查询列表
     */
    @RequestMapping(value = "/problemOfName",method = RequestMethod.GET)
    public Object problemOfName(HttpServletRequest request){
        String problemName = request.getParameter("problemName").trim();          //题目名字
        return problemService.problemOfName("%"+problemName+"%");
    }

    /**
     * 根据活动id和章节id和内容id查询题目对象
     */
    @RequestMapping(value = "/content/chapter/activity/detail", method = RequestMethod.GET)
    public Object problemOfActivityIDAndChapterIDAndContentID(HttpServletRequest request) {
        String activityID = request.getParameter("activityID");
        String chapterID = request.getParameter("chapterID");
        String contentID = request.getParameter("contentID");
        return problemService.problemOfActivityIDAndChapterIDAndContentID(Integer.parseInt(activityID), Integer.parseInt(chapterID), Integer.parseInt(contentID));
    }
}






















