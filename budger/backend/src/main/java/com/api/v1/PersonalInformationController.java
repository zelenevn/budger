package com.api.v1;

import com.data.dto.AccountDto;
import com.data.dto.PersonalInformationDto;
import com.data.entity.Account;
import com.data.entity.PersonalInformation;
import com.service.AccountService;
import com.service.PersonalInformationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(
        path = "api/v1/data"
)
@AllArgsConstructor
public class PersonalInformationController {


    private final AccountService accountService;
    private final PersonalInformationService personalInformationService;

    @GetMapping(
            path = "/personalInfo"
    )
    public PersonalInformationDto read(){

        Optional<Account> account = accountService.getByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        PersonalInformationDto dto = null;
        if (account.isPresent()) {
            Optional<PersonalInformation> personalInformation = personalInformationService.getByAccount(account.get());
            if (personalInformation.isPresent())
                dto = new PersonalInformationDto(personalInformation.get());
        }
        return dto;
    }

    @PutMapping(
            path = "/editPersInfo"
    )
    public void create(@RequestBody PersonalInformationDto personalInformationDto) {
            Optional<Account> account = accountService.getByEmail(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );
            PersonalInformationDto dto = null;
            if (account.isPresent())
                personalInformationService.createPersonalInformationByAccount(account.get(), personalInformationDto);

    }


}
