package com.javaclimb.ACoder.service;

import com.javaclimb.ACoder.domain.Content;

import java.util.List;

/**
 * 内容service接口
 */
public interface ContentService {
    /**
     *增加
     */
    public boolean insert(Content content);
    /**
     *修改
     */
    public boolean update(Content content);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
     * 根据活动id查询
     */
    public List<Content> contentOfActivityIDAndChapterID(Integer activityID, Integer chapterID);
}
