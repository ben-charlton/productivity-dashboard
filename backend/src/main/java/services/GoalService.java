package services;

import dto.GoalStatisticsDto;
import models.Goal;
import repositories.GoalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal createGoal(Goal goal) {
        // Additional validation or logic can be performed here before saving to the database
        return goalRepository.save(goal);
    }

    public List<Goal> getAllGoalsForUser(Long userId) {
        return goalRepository.findAllByUserId(userId);
    }

    public void updateGoalProgress(Long goalId, int hours) {
        goalRepository.updateCurrentHourProgress(goalId, hours);
    }

    public GoalStatisticsDto getGoalStatisticsForUser(Long userId) {
        List<Goal> goals = goalRepository.findAllByUserId(userId);
    
        int totalHoursWorked = goals.stream().mapToInt(Goal::getCurrentHourProgress).sum();
        double completionRate = (double) goals.stream().filter(goal -> goal.getCurrentHourProgress() >= goal.getDesiredHours()).count() / goals.size();
        List<Integer> progressOverTime = goals.stream().map(Goal::getCurrentHourProgress).collect(Collectors.toList());
    
        return new GoalStatisticsDto(totalHoursWorked, completionRate, progressOverTime);
    }
    
}
