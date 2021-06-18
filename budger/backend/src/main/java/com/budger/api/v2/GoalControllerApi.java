package com.budger.api.v2;

import com.budger.data.dto.CreateGoalDto;
import com.budger.data.dto.v2.goal.GetGoalDto;
import com.budger.data.dto.v2.goal.UpdateGoalDto;
import com.budger.services.GoalService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v2/goal"
)
public class GoalControllerApi {

    private final GoalService goalService;

    public GoalControllerApi(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public void create(@RequestBody CreateGoalDto goalDto) {
        String login = goalDto.getLogin();
        String title = goalDto.getTitle();
        float value = goalDto.getValue();
        String description = goalDto.getDescription();
        Date date = goalDto.getDate();
        goalService.create(
                login,
                title,
                value,
                date,
                description
        );
    }

    @GetMapping(
            path = "/{id}"
    )
    public GetGoalDto read(@PathVariable("id") Integer id) {
        return goalService.read(id);
    }

    @GetMapping(
            path = "/all"
    )
    public List<GetGoalDto> readAll(@RequestParam("owner") String owner) {
        return goalService.readAllGoals(owner);
    }

    @PutMapping(
            path = "/{id}"
    )
    public void update(@PathVariable("id") Integer id, UpdateGoalDto goalDto) {
        goalService.update(id, goalDto);
    }

    @DeleteMapping(
            path = "/{id}"
    )
    public void delete(@PathVariable("id") Integer id) {
        goalService.delete(id);
    }
}
