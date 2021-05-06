package com.data.dto;

import com.data.entity.Family;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FamilyDto {

    private Integer id;

    private String name;

    public FamilyDto(Family family) {
        this.id = family.getId();
        this.name = family.getName();
    }
}
