package hamsterresqueauth.dto.report;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDtoSimple {

    private String hamsterName;

    private LocalDate dateOfMeasure;

    private double weight;

    private String reportText;
}
