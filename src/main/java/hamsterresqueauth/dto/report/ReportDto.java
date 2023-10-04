package hamsterresqueauth.dto.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private String hamsterName;

    private LocalDate dateOfMeasure;

    private double weight;

    private String reportText;

    private String nameOfHost;

    public ReportDto(String hamsterName, LocalDate dateOfMeasure, double weight, String reportText) {
        this.hamsterName = hamsterName;
        this.dateOfMeasure = dateOfMeasure;
        this.weight = weight;
        this.reportText = reportText;
    }
}
