package com.data.dto;

import com.data.entity.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@AllArgsConstructor
@Getter
public class PersonalInformationDto {

    private String firstName;

    private String lastName;

    private Date birthdayDate;

    public PersonalInformationDto(PersonalInformation personalInformation) {
        this.firstName = personalInformation.getFirstName();
        this.lastName = personalInformation.getLastName();
        this.birthdayDate = personalInformation.getBirthdayDate();
    }

}
