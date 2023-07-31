package backend.src.main.java.repositories;
import backend.src.main.java.models.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    // Add any custom query methods as needed

    @Modifying
    @Query("UPDATE Goal g SET g.currentHourProgress = g.currentHourProgress + :hours WHERE g.id = :goalId")
    void updateCurrentHourProgress(@Param("goalId") Long goalId, @Param("hours") int hours);
}
