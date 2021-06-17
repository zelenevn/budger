package com.budger.data.dto;

import java.sql.Date;

public class TransactionDto {

    private float value;

    private Date date;

    private String category;

    public TransactionDto() {
    }

    public TransactionDto(float value, Date date, String category) {
        this.value = value;
        this.date = date;
        this.category = category;
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
