package com;

import com.data.dao.AccountRepository;
import com.data.dao.BudgetRepository;
import com.data.dao.UserRoleRepository;
import com.data.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository ar, BudgetRepository br, UserRoleRepository ur) {
        return args -> {
            Account account = ar.findAll().get(0);
            account.setUserRole(ur.findAll().get(0));
            ar.save(account);

        };
    }

}
