package repositories;

import models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    // Add any custom query methods as needed

    @Modifying
    @Query("UPDATE Goal g SET g.currentHourProgress = g.currentHourProgress + :hours WHERE g.id = :goalId")
    void updateCurrentHourProgress(@Param("goalId") Long goalId, @Param("hours") int hours);

    @Query("SELECT g FROM Goal g WHERE g.userId = :userId")
    List<Goal> findAllByUserId(Long userId);

}
