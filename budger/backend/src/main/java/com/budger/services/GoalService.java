package com.budger.services;

import com.budger.data.dto.GoalDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Goal;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.repositories.AccountRepository;
import com.budger.repositories.GoalRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final AccountRepository accountRepository;

    public GoalService(GoalRepository goalRepository, AccountRepository accountRepository) {
        this.goalRepository = goalRepository;
        this.accountRepository = accountRepository;
    }

    public void create(String login, String title, float value, Date date, String description) {

        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            Goal goal = new Goal(
                    title,
                    value,
                    description,
                    date,
                    account.getBudget()
            );

            goalRepository.save(goal);
        } else
            throw new AccountDoesNotExistsException("Account does not exists");
    }

    public List<GoalDto> readAll(String login) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<Goal> goals = account.getBudget().getGoals();
            List<GoalDto> goalDtos = new LinkedList<>();
            goals.forEach(goal -> {
                goalDtos.add(new GoalDto(
                        goal.getTitle(),
                        goal.getValue(),
                        goal.getExpirationDate(),
                        goal.getDescription()
                ));
            });
            return goalDtos;
        } else {
            throw new AccountDoesNotExistsException("Account does not exists.");
        }
    }
}
