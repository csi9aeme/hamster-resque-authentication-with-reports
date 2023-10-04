package hamsterresqueauth.dto.report;

import hamsterresqueauth.model.TemporaryHost;
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

    private TemporaryHost host;

    private String hamsterName;

    private LocalDate dateOfMeasure;

    private double weight;

    private String reportText;

}
