package com.budger.data.dto.v2.goal;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;

import java.util.Date;

@JsonTypeName("goal")
public class GetGoalDto {

    Integer id;

    String title;

    float value;

    String description;

    Date date;

    public GetGoalDto() {
    }

    public GetGoalDto(Integer id, String title, float value, String description, Date date) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
