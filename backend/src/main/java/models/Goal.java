package backend.src.main.java.models;
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private int desiredHourTarget;

    private int currentHourProgress;

    // Getters and setters
}
