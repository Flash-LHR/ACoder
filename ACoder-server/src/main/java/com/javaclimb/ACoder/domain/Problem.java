package com.javaclimb.ACoder.domain;

import java.io.Serializable;

/**
 * 内容
 */
public class Problem implements Serializable {
    /*主键*/
    private Integer id;
    /*活动id*/
    private Integer activityID;
    /*章节id*/
    private Integer ChapterID;
    /*内容id*/
    private Integer ContentID;
    /*题目id*/
    private Integer ProblemID;
    /*题目名*/
    private String ProblemName;
    /*题目链接*/
    private String ProblemLink;
    /*题目状态*/
    private String ProblemStatus;

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

    public Integer getProblemID() {
        return ProblemID;
    }

    public void setProblemID(Integer problemID) {
        ProblemID = problemID;
    }

    public String getProblemName() {
        return ProblemName;
    }

    public void setProblemName(String problemName) {
        ProblemName = problemName;
    }

    public String getProblemLink() {
        return ProblemLink;
    }

    public void setProblemLink(String problemLink) {
        ProblemLink = problemLink;
    }

    public String getProblemStatus() {
        return ProblemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        ProblemStatus = problemStatus;
    }
}
