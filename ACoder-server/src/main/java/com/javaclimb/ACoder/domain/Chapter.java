package com.javaclimb.ACoder.domain;

import java.io.Serializable;

/**
 * 章节
 */
public class Chapter implements Serializable {
    /*主键*/
    private Integer id;
    /*活动id*/
    private Integer activityID;
    /*章节id*/
    private Integer ChapterID;
    /*章节名*/
    private String ChapterName;

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

    public String getChapterName() {
        return ChapterName;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }
}
