package com.javaclimb.ACoder.service;

import com.javaclimb.ACoder.domain.Chapter;

import java.util.List;

/**
 * 章节service接口
 */
public interface ChapterService {
    /**
     *增加
     */
    public boolean insert(Chapter chapter);
    /**
     *修改
     */
    public boolean update(Chapter chapter);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Chapter selectByPrimaryKey(Integer id);

    /**
     * 查询所有章节
     */
    public List<Chapter> allChapter();

    /**
     * 根据章节名字模糊查询列表
     */
    public List<Chapter> chapterOfName(String chapterName);
    /**
     * 根据活动id查询
     */
    public List<Chapter> chapterOfActivityID(Integer activityID);
}
