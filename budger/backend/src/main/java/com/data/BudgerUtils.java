package com.data;

import com.data.dto.SummaryDto;
import com.data.entity.Account;
import com.data.entity.Category;
import com.data.entity.Transaction;
import com.service.AccountService;
import com.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BudgerUtils {

    private final AccountService accountService;
    private final CategoryService categoryService;

    public Account getCurrUser(){
        return accountService.getByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get();
    }

    public List<SummaryDto> summary(List<Transaction> transactions){

        List<SummaryDto> list = new ArrayList<>();
        //TODO Добавить конвертацию
        Map<Category,List<Transaction>> map = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory));

        categoryService.findAll().forEach( x-> list.add(new SummaryDto(
                x.getCategoryName(),
                map.get(x).stream().mapToDouble(a -> a.getValue()).sum())));
        list.sort((o1, o2) -> (int)(o2.getSum()-o1.getSum()));
        return list;

    }

}
