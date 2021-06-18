package com.budger.services;

import com.budger.data.dto.GoalDto;
import com.budger.data.dto.v2.goal.GetGoalDto;
import com.budger.data.dto.v2.goal.UpdateGoalDto;
import com.budger.data.dto.v2.transaction.GetTransactionDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Goal;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.exceptions.GoalDoesNotExistsException;
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

    public GetGoalDto read(Integer id) {
        Optional<Goal> goalOptional = goalRepository.findById(id);
        if (goalOptional.isPresent()) {
            Goal goal = goalOptional.get();
            GetGoalDto goalDto = new GetGoalDto(
                    goal.getId(),
                    goal.getTitle(),
                    goal.getValue(),
                    goal.getDescription(),
                    goal.getExpirationDate()
            );

            return goalDto;
        } else
            throw new GoalDoesNotExistsException(String.format("Goal with %d id does not exists", id));
    }

    public List<GetGoalDto> readAllGoals(String login) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<Goal> goals = account.getBudget().getGoals();
            List<GetGoalDto> goalDtos = new LinkedList<>();
            goals.forEach(goal -> {
                goalDtos.add(new GetGoalDto(
                        goal.getId(),
                        goal.getTitle(),
                        goal.getValue(),
                        goal.getDescription(),
                        goal.getExpirationDate()
                ));
            });
            return goalDtos;
        } else {
            throw new AccountDoesNotExistsException("Account does not exists.");
        }
    }

    public void delete(Integer id) {
        Optional<Goal> goalOptional = goalRepository.findById(id);
        if (goalOptional.isPresent()) {
            Goal goal = goalOptional.get();
            goalRepository.delete(goal);
        } else
            throw new GoalDoesNotExistsException(String.format("Goal with %d id does not exists", id));
    }

    public void update(Integer id, UpdateGoalDto goalDto) {
        throw new UnsupportedOperationException();
    }
}
