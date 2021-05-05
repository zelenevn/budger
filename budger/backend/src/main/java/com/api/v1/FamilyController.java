package com.api.v1;

import com.data.dto.AccountDto;
import com.data.dto.FamilyDto;
import com.data.dto.SummaryDto;
import com.data.dto.TransactionDto;
import com.data.entity.Account;
import com.data.entity.Family;
import com.data.BudgerUtils;
import com.data.entity.Transaction;
import com.service.AccountService;
import com.service.FamilyService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        path = "api/v1/families"
)
@AllArgsConstructor
public class FamilyController {

    private final FamilyService familyService;
    private final AccountService accountService;
    private final BudgerUtils budgerUtils;

    @GetMapping(
            path = "/myFamilies"
    )
    public List<FamilyDto> readFamilies(){

        Account account = budgerUtils.getCurrUser();
        List<FamilyDto> list = new ArrayList<>();
        account.getFamilies().forEach( (x) -> list.add(new FamilyDto(x)));
        return list;
    }
    @PutMapping(
            path = "/invite/{id}"
    )
    public void enterFamily(@PathVariable Integer id){

        Account account = budgerUtils.getCurrUser();
        Optional<Family> familyOpt = familyService.getById(id);

        if (familyOpt.isPresent()) {
            Family family = familyOpt.get();
            account.getFamilies().add(family);
            accountService.update(account.getId(), account);
        }
    }

    @PutMapping(
            path = "/create"
    )
    public void createFamily(@RequestBody FamilyDto dto){

        int id = familyService.createFromDto(dto);
        Account account = budgerUtils.getCurrUser();
        account.getFamilies().add(familyService.getById(id).get());
        accountService.update(account.getId(),account);
    }
    @GetMapping(
            path = "/{id}/members"
    )
    public List<AccountDto> readMembers(@PathVariable Integer id){

        Optional<Family> familyOpt = familyService.getById(id);
        if (familyOpt.isPresent() && budgerUtils.getCurrUser().getFamilies().contains(familyOpt.get())){
            Family family = familyOpt.get();
            if (!budgerUtils.getCurrUser().getFamilies().contains(family))
                return null;

            List<AccountDto> list = new ArrayList<>();

            family.getAccounts().forEach((x)-> list.add(new AccountDto(x.getSecondUsername(),null)));
            return list;
        }
        return null;
    }
    @GetMapping(
            path = "{id}/transactions"
    )
    public List<TransactionDto> readFamilyTransactions(@PathVariable Integer id) {

        Optional<Family> familyOpt = familyService.getById(id);
        if (familyOpt.isPresent() && budgerUtils.getCurrUser().getFamilies().contains(familyOpt.get())) {
            List<TransactionDto> list = new ArrayList<>();
            Family family = familyOpt.get();
            if (!budgerUtils.getCurrUser().getFamilies().contains(family))
                return null;
            family.getAccounts().forEach( x -> x.getBudget().getTransactions().forEach(y -> {
                list.add(new TransactionDto(y,x));
                    }));
            list.sort((o1, o2) -> o2.getTransactionTime().compareTo(o1.getTransactionTime()));
            return list;
        }
        return null;
    }
    @GetMapping(
            path = "/{id}/helper"
    )
    public List<SummaryDto> helper(@PathVariable Integer id){

        Optional<Family> familyOpt = familyService.getById(id);
        if (familyOpt.isPresent() && budgerUtils.getCurrUser().getFamilies().contains(familyOpt.get())) {
            List<Transaction> list = new ArrayList<>();
            familyOpt.get().getAccounts().forEach(a -> list.addAll(a.getBudget().getTransactions()));
            return budgerUtils.summary(list);
        }
        return null;
    }

}
