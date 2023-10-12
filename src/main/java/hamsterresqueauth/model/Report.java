package hamsterresqueauth.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String hamsterName;

    @Column(name = "date_of_measure")
    private LocalDate dateOfMeasure;

    private double weight;

    @Column(name = "report_text")
    private String reportText;


    public Report(User user, String hamsterName, LocalDate dateOfMeasure, double weight, String reportText) {
        this.user = user;
        this.hamsterName = hamsterName;
        this.dateOfMeasure = dateOfMeasure;
        this.weight = weight;
        this.reportText = reportText;
    }
}
