package com.budger.api;

import com.budger.data.dto.GetResourceDto;
import com.budger.data.dto.RecommendationDto;
import com.budger.services.RecommendationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/recommendations"
)
@CrossOrigin("*")
public class RecommendationsController {

    private final RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    @PostMapping
    public List<RecommendationDto> getRecommendations(@RequestBody GetResourceDto getResourceDto) {
        String login = getResourceDto.getLogin();
        return recommendationsService.getRecommendations(login);
    }
}
