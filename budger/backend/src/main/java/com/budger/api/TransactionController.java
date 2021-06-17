package com.budger.api;

import com.budger.data.dto.CreateTransactionDto;
import com.budger.data.dto.GetResourceDto;
import com.budger.data.dto.TransactionDto;
import com.budger.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(
        path = "/transaction"
)
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void create(@RequestBody CreateTransactionDto createTransactionDto) {
        String login = createTransactionDto.getLogin();
        float value = createTransactionDto.getValue();
        Date date = createTransactionDto.getDate();
        String category = createTransactionDto.getCategory();
        transactionService.create(login, value, date, category);
    }

    @GetMapping
    public void read() {

    }

    @PostMapping(
            path = "/all"
    )
    public List<TransactionDto> readAll(@RequestBody GetResourceDto getResourceDto) {
        String login = getResourceDto.getLogin();
        return transactionService.readAll(login);
    }
}
