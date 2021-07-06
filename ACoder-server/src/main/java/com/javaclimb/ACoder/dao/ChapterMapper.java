package com.javaclimb.ACoder.dao;

import com.javaclimb.ACoder.domain.Chapter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 章节Dao
 */
@Repository
public interface ChapterMapper {
    /**
     *增加
     */
    public int insert(Chapter chapter);
    /**
     *修改
     */
    public int update(Chapter chapter);

    /**
     * 删除
     */
    public int delete(Integer id);

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
















