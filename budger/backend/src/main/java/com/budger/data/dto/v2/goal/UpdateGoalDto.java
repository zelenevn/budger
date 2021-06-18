package com.budger.data.dto.v2.goal;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;

import java.sql.Date;

@JsonTypeName("goal")
public class UpdateGoalDto {

    String title;
    float value;
    String description;
    Date date;

    public UpdateGoalDto() {
    }

    public UpdateGoalDto(String title, float value, String description, Date date) {
        this.title = title;
        this.value = value;
        this.description = description;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
