package com.javaclimb.ACoder.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 */
public class Activity implements Serializable {
    /*主键*/
    private Integer id;
    /*活动id*/
    private Integer activityID;
    /*活动名*/
    private String activityName;

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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
