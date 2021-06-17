package com.budger.data.dto;

public class CategoryDto {

    private String title;

    public CategoryDto() {
    }

    public CategoryDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
