package com.javaclimb.ACoder.service.impl;

import com.javaclimb.ACoder.dao.ProblemMapper;
import com.javaclimb.ACoder.domain.Problem;
import com.javaclimb.ACoder.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目service实现类
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    /**
     * 增加
     *
     * @param problem
     */
    @Override
    public boolean insert(Problem problem) {
        return problemMapper.insert(problem)>0;
    }
    /**
     * 修改
     *
     * @param problem
     */
    @Override
    public boolean update(Problem problem) { return problemMapper.update(problem)>0; }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {

        return problemMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Problem selectByPrimaryKey(Integer id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有题目
     */
    @Override
    public List<Problem> allProblem() {
        return problemMapper.allProblem();
    }

    /**
     * 根据题目名字模糊查询列表
     *
     * @param problemName
     */
    @Override
    public List<Problem> problemOfName(String problemName) {
        return problemMapper.problemOfName(problemName);
    }

    /**
     * 根据活动id和章节id和内容id查询
     *
     * @param activityID,chapterID,contentID
     */
    @Override
    public List<Problem> problemOfActivityIDAndChapterIDAndContentID(Integer activityID, Integer chapterID, Integer contentID) {
        return problemMapper.problemOfActivityIDAndChapterIDAndContentID(activityID, chapterID, contentID);
    }

}
