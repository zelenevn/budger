package com.budger.services;

import com.budger.data.dto.ReportDto;
import com.budger.data.dto.TransactionDto;
import com.budger.data.entities.Account;
import com.budger.exceptions.AccountDoesNotExistsException;
import com.budger.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final AccountRepository accountRepository;

    public ReportService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<TransactionDto> getReport(String login, Date startDate, Date endDate) {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            List<TransactionDto> transactionDtos = new LinkedList<>();
            account.getBudget().getTransactions().forEach(transaction -> {
                Date transactionDate = transaction.getDate();
                if (transactionDate.after(startDate) && transactionDate.before(endDate)) {
                    transactionDtos.add(new TransactionDto(
                            transaction.getValue(),
                            transactionDate,
                            transaction.getCategory().getCategoryName()
                    ));
                }

            });
            return transactionDtos;

        } else
            throw new AccountDoesNotExistsException("Account does not exists.");
    }
}
