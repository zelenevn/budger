package com.budger.api.v2;

import com.budger.data.dto.CreateTransactionDto;
import com.budger.data.dto.TransactionDto;
import com.budger.data.dto.v2.transaction.GetTransactionDto;
import com.budger.data.dto.v2.transaction.UpdateTransactionDto;
import com.budger.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v2/transaction"
)
public class TransactionControllerApi {

    private final TransactionService transactionService;

    public TransactionControllerApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateTransactionDto createTransactionDto) {
        transactionService.create(
                createTransactionDto.getLogin(),
                createTransactionDto.getValue(),
                createTransactionDto.getDate(),
                createTransactionDto.getCategory()
                );
    }

    @GetMapping(
            path = "/{id}"
    )
    public GetTransactionDto read(@PathVariable("id") Integer id) {
        return transactionService.read(id);
    }

    @GetMapping(
            path = "/all",
            params = {"owner"}
    )
    //Owner param is user login
    public List<GetTransactionDto> readAll(@RequestParam String owner) {
        return transactionService.readAllTransactions(owner);
    }

    @PutMapping(
            path = "/{id}"
    )
    public void update(@PathVariable("id") Integer id, @RequestBody UpdateTransactionDto updateTransactionDto) {

    }

    @DeleteMapping(
            path = "/{id}"
    )
    public void delete(@PathVariable("id") Integer id) {
        transactionService.delete(id);
    }
}
