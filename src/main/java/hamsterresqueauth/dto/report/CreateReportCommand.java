package hamsterresqueauth.dto.report;

import hamsterresqueauth.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReportCommand {

    private User host;

    private String hamsterName;

    private LocalDate dateOfMeasure;

    private double weight;

    private String reportText;

}
