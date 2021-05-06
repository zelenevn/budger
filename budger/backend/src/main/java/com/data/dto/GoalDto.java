package com.data.dto;

import com.data.entity.Account;
import com.data.entity.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoalDto {

    private String owner;

    private String title;

    private Float value;

    private String currency;

    private String description;

    private Timestamp expDate;

    public GoalDto(Goal goal, Account account) {
        this.owner = account.getSecondUsername();
        this.title = goal.getTitle();
        this.value = goal.getValue();
        this.currency = goal.getCurrency();
        this.description = getDescription();
        this.expDate = goal.getExpirationDate();
    }
}
