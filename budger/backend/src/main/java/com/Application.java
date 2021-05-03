package com;

import com.data.dao.AccountRepository;
import com.data.dao.BudgetRepository;
import com.data.dao.UserRoleRepository;
import com.data.entity.Account;
import com.data.entity.PersonalInformation;
import com.service.AccountService;
import com.service.PersonalInformationService;
import com.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService as, PersonalInformationService ps, UserRoleService us) {
        return args -> {
            PersonalInformation pi = new PersonalInformation(as.findAll().get(0),"sas","sas", new Date(12312));
            ps.save(pi);
            us.findAll();
        };
    }

}
