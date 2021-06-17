package com.budger.data.dto;

import java.sql.Date;

public class GetReportDto {

    String login;

    Date startDate;

    Date endDate;

    public GetReportDto() {
    }

    public GetReportDto(String login, Date startDate, Date endDate) {
        this.login = login;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
