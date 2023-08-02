package controllers;

import models.Goal;
import services.GoalService;
import dto.GoalStatisticsDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    @GetMapping("/{userId}")
    public List<Goal> getAllGoalsForUser(@PathVariable Long userId) {
        return goalService.getAllGoalsForUser(userId);
    }

    @PutMapping("/{goalId}")
    public void updateGoalProgress(@PathVariable Long goalId, @RequestParam int hours) {
        goalService.updateGoalProgress(goalId, hours);
    }

    @GetMapping("/user/{userId}/statistics")
    public GoalStatisticsDto getGoalStatisticsForUser(@PathVariable Long userId) {
    return goalService.getGoalStatisticsForUser(userId);
    }
}
