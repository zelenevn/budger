package com.api.v1;

import com.data.dto.CategoryDto;
import com.data.dto.GoalDto;
import com.data.dto.SummaryDto;
import com.data.dto.TransactionDto;
import com.data.entity.Account;
import com.data.entity.Budget;
import com.data.entity.Transaction;
import com.data.BudgerUtils;
import com.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(
        path = "api/v1/budget"
)
@AllArgsConstructor
public class UserController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final GoalService goalService;
    private final BudgerUtils budgerUtils;

    @GetMapping(
            path = "/"
    )
    public List<TransactionDto> readTransactions(){

        Account account = budgerUtils.getCurrUser();
        List<TransactionDto> list = new ArrayList<>();
        Budget budget = account.getBudget();
        for (Transaction tr:budget.getTransactions())
            list.add(new TransactionDto(tr,account));
        return list;
    }
    @PutMapping(
            path = "/"
    )
    @Operation(
            description = "В TransactionDto поле owner можно не заполнять - оно используется только для вывода в контексте семьи"
    )
    public void addTransaction(@RequestBody TransactionDto dto){

            Account account = budgerUtils.getCurrUser();
            Budget budget = account.getBudget();
            transactionService.createTransactionFromDto(budget, dto);
    }
    @GetMapping(
            path = "/categories"
    )
    public List<CategoryDto> readCategories(){
        List<CategoryDto> list = new ArrayList<>();
        categoryService.findAll().forEach((x) -> list.add(new CategoryDto(x)));
        return list;
    }
    @GetMapping(
            path = "/goals"
    )
    public List<GoalDto> readGoals(){
        List<GoalDto> list = new ArrayList<>();
        Account account = budgerUtils.getCurrUser();
        account.getBudget().getGoals().forEach((x) -> list.add(new GoalDto(x,account)));
        return list;
    }
    @PutMapping(
            path = "/goals"
    )
    @Operation(
            description = "В GoalDto поле owner можно не заполнять - оно используется только для вывода в контексте семьи"
    )
    public void createGoal(@RequestBody GoalDto dto){
        Account account = budgerUtils.getCurrUser();
        goalService.createFromDto(account.getBudget(),dto);

    }
    @GetMapping(
            path = "/helper"
    )
    public List<SummaryDto> helper(){

        return budgerUtils.summary(
                budgerUtils.getCurrUser().getBudget().getTransactions()
        );

    }

}
