package com.javaclimb.ACoder.service.impl;

import com.javaclimb.ACoder.dao.ContentMapper;
import com.javaclimb.ACoder.domain.Content;
import com.javaclimb.ACoder.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容service实现类
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    /**
     * 增加
     *
     * @param content
     */
    @Override
    public boolean insert(Content content) {
        return contentMapper.insert(content)>0;
    }
    /**
     * 修改
     *
     * @param content
     */
    @Override
    public boolean update(Content content) { return contentMapper.update(content)>0; }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {

        return contentMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Content selectByPrimaryKey(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有活动
     */
    @Override
    public List<Content> allContent() {
        return contentMapper.allContent();
    }

    /**
     * 根据活动名字模糊查询列表
     *
     * @param contentName
     */
    @Override
    public List<Content> contentOfName(String contentName) {
        return contentMapper.contentOfName(contentName);
    }

    /**
     * 根据活动id和章节id查询
     *
     * @param activityID,chapterID
     */
    @Override
    public List<Content> contentOfActivityIDAndChapterID(Integer activityID, Integer chapterID) {
        return contentMapper.contentOfActivityIDAndChapterID(activityID, chapterID);
    }

}
