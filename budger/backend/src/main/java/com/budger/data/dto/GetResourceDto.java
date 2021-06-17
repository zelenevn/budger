package com.budger.data.dto;

public class GetResourceDto {

    private String login;

    public GetResourceDto() {
    }

    public GetResourceDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
