package com.budger.api.v1;

import com.budger.data.dto.CreateGoalDto;
import com.budger.data.dto.GetResourceDto;
import com.budger.data.dto.GoalDto;
import com.budger.services.GoalService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(
        path = "/goal"
)
@CrossOrigin("*")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public void create(@RequestBody CreateGoalDto goalDto) {
        String login = goalDto.getLogin();
        String title = goalDto.getTitle();
        float value = goalDto.getValue();
        Date date = goalDto.getDate();
        String description = goalDto.getDescription();

        goalService.create(login, title, value, date, description);
    }

    @PostMapping(
            path = "/all"
    )
    public List<GoalDto> readAll(@RequestBody GetResourceDto getResourceDto) {
        String login = getResourceDto.getLogin();
        return goalService.readAll(login);
    }
}
