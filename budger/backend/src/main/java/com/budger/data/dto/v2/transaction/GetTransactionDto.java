package com.budger.data.dto.v2.transaction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;

import java.sql.Date;

@JsonTypeName("transaction")
public class GetTransactionDto {

    Integer id;
    float value;
    Date date;
    String category;

    public GetTransactionDto() {
    }

    public GetTransactionDto(Integer id, float value, Date date, String category) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
