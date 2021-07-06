package com.javaclimb.ACoder.service.impl;

import com.javaclimb.ACoder.dao.ChapterMapper;
import com.javaclimb.ACoder.domain.Chapter;
import com.javaclimb.ACoder.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 章节service实现类
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 增加
     *
     * @param chapter
     */
    @Override
    public boolean insert(Chapter chapter) {
        return chapterMapper.insert(chapter)>0;
    }
    /**
     * 修改
     *
     * @param chapter
     */
    @Override
    public boolean update(Chapter chapter) { return chapterMapper.update(chapter)>0; }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {

        return chapterMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Chapter selectByPrimaryKey(Integer id) {
        return chapterMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有活动
     */
    @Override
    public List<Chapter> allChapter() {
        return chapterMapper.allChapter();
    }

    /**
     * 根据活动名字模糊查询列表
     *
     * @param chapterName
     */
    @Override
    public List<Chapter> chapterOfName(String chapterName) {
        return chapterMapper.chapterOfName(chapterName);
    }

    /**
     * 根据活动id查询
     *
     * @param activityID
     */
    @Override
    public List<Chapter> chapterOfActivityID(Integer activityID) {
        return chapterMapper.chapterOfActivityID(activityID);
    }

}
