package backend.src.main.java.services;
import backend.src.main.java.dto.GoalStatisticsDto;
import backend.src.main.java.models.Goal;
import backend.src.main.java.repositories.GoalRepository;

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
        double completionRate = (double) goals.stream().filter(goal -> goal.getCurrentHourProgress() >= goal.getDesiredHourTarget()).count() / goals.size();
        List<Integer> progressOverTime = goals.stream().map(Goal::getCurrentHourProgress).collect(Collectors.toList());
    
        return new GoalStatisticsDto(totalHoursWorked, completionRate, progressOverTime);
    }
    
}
