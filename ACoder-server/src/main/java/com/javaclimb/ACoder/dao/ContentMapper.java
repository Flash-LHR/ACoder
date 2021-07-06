package com.javaclimb.ACoder.dao;

import com.javaclimb.ACoder.domain.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 内容Dao
 */
@Repository
public interface ContentMapper {
    /**
     *增加
     */
    public int insert(Content content);
    /**
     *修改
     */
    public int update(Content content);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Content selectByPrimaryKey(Integer id);

    /**
     * 查询所有内容
     */
    public List<Content> allContent();

    /**
     * 根据内容名字模糊查询列表
     */
    public List<Content> contentOfName(String contentName);

    /**
     * 根据活动id和章节id查询
     */
    public List<Content> contentOfActivityIDAndChapterID(Integer activityID, Integer chapterID);
}
















