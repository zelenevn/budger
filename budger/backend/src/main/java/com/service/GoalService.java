package com.service;

import com.data.dao.FamilyRepository;
import com.data.dao.GoalRepository;
import com.data.dto.GoalDto;
import com.data.entity.Budget;
import com.data.entity.Family;
import com.data.entity.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private GoalRepository repository;

    @Autowired
    public void setRepository(GoalRepository repository){ this.repository = repository; }

    public Goal getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Goal goal) {
        repository.save(goal);
    }

    public void update(Integer id, Goal goal) {
        Goal updated = repository.findById(id).orElse(null);
        if (updated!=null) {
            updated.setBudget(goal.getBudget());
            updated.setCurrency(goal.getCurrency());
            updated.setDescription(goal.getDescription());
            updated.setExpirationDate(goal.getExpirationDate());
            updated.setTitle(goal.getTitle());
            updated.setValue(goal.getValue());
            repository.save(updated);
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Integer createFromDto(Budget budget, GoalDto goalDto){

        Goal goal = new Goal();
        goal.setValue(goalDto.getValue());
        goal.setTitle(goalDto.getTitle());
        goal.setExpirationDate(goalDto.getExpDate());
        goal.setDescription(goalDto.getDescription());
        goal.setCurrency(goalDto.getCurrency());
        goal.setBudget(budget);
        save(goal);
        return goal.getId();

    }

    public List<Goal> findAll() {
        return repository.findAll();
    }

}
