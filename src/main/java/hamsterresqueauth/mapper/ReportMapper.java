package hamsterresqueauth.mapper;

import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.model.Report;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportDto toReportDto(Report report);

    Set<ReportDto> toReportDto(Set<Report> reports);
}
