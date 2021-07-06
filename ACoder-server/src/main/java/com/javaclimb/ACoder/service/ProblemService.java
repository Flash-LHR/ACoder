package com.javaclimb.ACoder.service;

import com.javaclimb.ACoder.domain.Problem;

import java.util.List;

/**
 * 题目service接口
 */
public interface ProblemService {
    /**
     *增加
     */
    public boolean insert(Problem problem);
    /**
     *修改
     */
    public boolean update(Problem problem);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Problem selectByPrimaryKey(Integer id);

    /**
     * 查询所有内容
     */
    public List<Problem> allProblem();

    /**
     * 根据内容名字模糊查询列表
     */
    public List<Problem> problemOfName(String problemName);
    /**
     * 根据活动id查询
     */
    public List<Problem> problemOfActivityIDAndChapterIDAndContentID(Integer activityID, Integer chapterID, Integer contentID);
}
