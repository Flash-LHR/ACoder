package com.javaclimb.ACoder.domain;

import java.io.Serializable;

/**
 * 内容
 */
public class Content implements Serializable {
    /*主键*/
    private Integer id;
    /*活动id*/
    private Integer activityID;
    /*章节id*/
    private Integer ChapterID;
    /*内容id*/
    private Integer ContentID;
    /*内容名*/
    private String ContentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public Integer getChapterID() {
        return ChapterID;
    }

    public void setChapterID(Integer chapterID) {
        ChapterID = chapterID;
    }

    public Integer getContentID() {
        return ContentID;
    }

    public void setContentID(Integer contentID) {
        ContentID = contentID;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String contentName) {
        ContentName = contentName;
    }
}
