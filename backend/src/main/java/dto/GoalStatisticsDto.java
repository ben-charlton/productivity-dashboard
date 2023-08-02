package dto;
import java.util.List;

public class GoalStatisticsDto {
    private int totalHoursWorked;
    private double completionRate;
    private List<Integer> progressOverTime;

    public GoalStatisticsDto() {
        // Default constructor (required for deserialization)
    }

    public GoalStatisticsDto(int totalHoursWorked, double completionRate, List<Integer> progressOverTime) {
        this.totalHoursWorked = totalHoursWorked;
        this.completionRate = completionRate;
        this.progressOverTime = progressOverTime;
    }

    // Getter methods
    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public List<Integer> getProgressOverTime() {
        return progressOverTime;
    }

    // Setter methods
    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public void setProgressOverTime(List<Integer> progressOverTime) {
        this.progressOverTime = progressOverTime;
    }

}
