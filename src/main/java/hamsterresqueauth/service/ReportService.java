package hamsterresqueauth.service;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.exception.UserNotLoggedInYetException;
import hamsterresqueauth.mapper.ReportMapper;
import hamsterresqueauth.model.Report;
import hamsterresqueauth.model.TemporaryHost;
import hamsterresqueauth.repository.ReportRepository;
import hamsterresqueauth.repository.TemporaryHostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    private final TemporaryHostRepository hostRepository;

    private final ReportMapper reportMapper;

    public ReportDto createReport(CreateReportCommand command) {

        String hostEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if(hostEmail != null) {
            TemporaryHost loggedInHost = hostRepository.findByEmail(hostEmail).orElseThrow();

            Report report = Report.builder()
                    .host(loggedInHost)
                    .dateOfMeasure(LocalDate.now())
                    .hamsterName(command.getHamsterName())
                    .weight(command.getWeight())
                    .reportText(command.getReportText()).build();

            reportRepository.save(report);

            return reportMapper.toReportDto(report);
        }

        throw new UserNotLoggedInYetException();

    }
}
