package com.budger.data.dto.v2.transaction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@JsonTypeName("transaction")
public class UpdateTransactionDto {

    @NotBlank
    float value;

    @NotNull
    Date date;

    @NotBlank
    String category;

    public UpdateTransactionDto() {
    }

    public UpdateTransactionDto(float value, Date date, String category) {
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
