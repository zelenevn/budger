package com.budger.data.dto;

import java.sql.Date;

public class CreateTransactionDto {

    private String login;

    private float value;

    private Date date;

    private String category;

    public CreateTransactionDto() {
    }

    public CreateTransactionDto(String login, float value, Date date, String category) {
        this.login = login;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
