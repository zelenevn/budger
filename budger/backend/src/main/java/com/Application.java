package com;

import com.data.dao.AccountRepository;
import com.data.dao.BudgetRepository;
import com.data.dao.UserRoleRepository;
import com.data.entity.Account;
import com.data.entity.PersonalInformation;
import com.service.AccountService;
import com.service.PersonalInformationService;
import com.service.UserRoleService;
import io.swagger.v3.core.model.ApiDescription;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.sql.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService as, PersonalInformationService ps, UserRoleService us) {
        return args -> {

        };
    }

}
