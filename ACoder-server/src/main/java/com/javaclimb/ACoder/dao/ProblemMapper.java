package com.javaclimb.ACoder.dao;

import com.javaclimb.ACoder.domain.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目Dao
 */
@Repository
public interface ProblemMapper {
    /**
     *增加
     */
    public int insert(Problem problem);
    /**
     *修改
     */
    public int update(Problem problem);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Problem selectByPrimaryKey(Integer id);

    /**
     * 查询所有内容
     */
    public List<Problem> allProblem();

    /**
     * 根据题目名字模糊查询列表
     */
    public List<Problem> problemOfName(String problemName);

    /**
     * 根据活动id和章节id和内容id查询
     */
    public List<Problem> problemOfActivityIDAndChapterIDAndContentID(Integer activityID, Integer chapterID, Integer contentID);
}
















