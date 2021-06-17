package com.budger.data.dto;

import java.sql.Date;

public class GoalDto {

    private String title;

    private float value;

    private Date date;

    private String description;

    public GoalDto() {
    }

    public GoalDto(String title, float value, Date date, String description) {
        this.title = title;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
