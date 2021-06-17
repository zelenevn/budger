package com.budger.services;

import com.budger.data.dto.RecommendationDto;
import com.budger.data.entities.Account;
import com.budger.data.entities.Transaction;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecommendationsService {

    private final AccountRepository accountRepository;

    public RecommendationsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<RecommendationDto> getRecommendations(String login) {

        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<Transaction> transactions = account.getBudget().getTransactions();
            Set<RecommendationDto> recommendationDtos = new LinkedHashSet<>();
            transactions.sort((o1, o2) -> Float.compare(o2.getValue(), o1.getValue()));
            for (Transaction transaction : transactions) {
                if (recommendationDtos.size() >= 3) {
                    return new LinkedList<>(recommendationDtos);
                }
                if (!transaction.getCategory().getCategoryName().equals("Доход")) {
                    recommendationDtos.add(new RecommendationDto(transaction.getCategory().getCategoryName()));
                }
            }

            return new LinkedList<>(recommendationDtos);
        } else {
            throw new AccountDoesNotExistsException("Account does not exists");
        }
    }
}
