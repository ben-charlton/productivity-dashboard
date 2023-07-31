import backend.src.main.java.models.Goal;
import backend.src.main.java.models.GoalService;
import backend.src.main.java.models.GoalStatisticsDto;

package backend.src.main.java.controllers;
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
