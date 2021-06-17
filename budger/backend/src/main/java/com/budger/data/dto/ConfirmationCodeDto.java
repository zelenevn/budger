package com.budger.data.dto;

public class ConfirmationCodeDto {

    private String code;

    public ConfirmationCodeDto() {
    }

    public ConfirmationCodeDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
