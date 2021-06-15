package com.api.v1;

import com.data.dto.CategoryDto;
import com.data.dto.GoalDto;
import com.data.dto.SummaryDto;
import com.data.dto.TransactionDto;
import com.data.entity.*;
import com.data.BudgerUtils;
import com.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    private final FamilyService familyService;
    private final PersonalInformationService personalInformationService;
    private final BudgetService budgetService;
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

    @DeleteMapping(
            path = "transactions/delete/{id}"
    )
    public void deleteTransaction(@PathVariable Integer id){
        Account account = budgerUtils.getCurrUser();
        if (!account.getId().equals(id) &&
                !budgerUtils.getCurrRole().getAuthority().equals("Admin")) {
            //TODO: return error page
            return;
        }
        Optional<Transaction> opt = transactionService.getById(id);

        if (opt.isPresent())
            if (opt.get().getBudget().getId().equals(account.getBudget().getId()) ||
                budgerUtils.getCurrRole().getAuthority().equals("Admin"))
                transactionService.delete(id);
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

    @DeleteMapping(
            path = "/goals/{id}"
    )
    public void deleteGoal(@PathVariable Integer id){
        Account account = budgerUtils.getCurrUser();
        if (!account.getId().equals(id) &&
                !budgerUtils.getCurrRole().getAuthority().equals("Admin")) {
            //TODO: return error page
            return;
        }
        Optional<Goal> opt = goalService.getById(id);
        if (opt.isPresent())
            if (opt.get().getBudget().getId().equals(account.getBudget().getId()) ||
                budgerUtils.getCurrRole().getAuthority().equals("Admin"))
                goalService.delete(id);

    }

    @DeleteMapping(
            "/delete/{id}"
    )
    public String delete(@PathVariable Integer id){

        Account account = budgerUtils.getCurrUser();
        if (!account.getId().equals(id) &&
                !budgerUtils.getCurrRole().getAuthority().equals("Admin")) {
            //TODO: return error page
            return "error";
        }
        List<Family> list = accountService.getById(id).getFamilies();
        for (Family f : list) {
            if (f.getAccounts().size() == 1)
                familyService.delete(f.getId());
        }
        Integer budgetId = account.getBudget().getId();
        personalInformationService.delete(id);
        budgetService.delete(budgetId);

        if (account.getId().equals(id)) {
            budgerUtils.logout();
            return "uspeh";
        }
        else return "admin";


    }


    @GetMapping(
            path = "/helper"
    )
    public List<SummaryDto> helper() {

        return budgerUtils.summary(
                budgerUtils.getCurrUser().getBudget().getTransactions()
        );

    }

}
