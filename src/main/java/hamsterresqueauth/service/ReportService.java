package hamsterresqueauth.service;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.exception.UserNotLoggedInYetException;
import hamsterresqueauth.mapper.ReportMapper;
import hamsterresqueauth.model.Report;
import hamsterresqueauth.model.User;
import hamsterresqueauth.repository.ReportRepository;
import hamsterresqueauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReportService {
    private  ReportRepository reportRepository;

    private UserRepository hostRepository;

    private  ReportMapper reportMapper;

    public ReportDto createReport(CreateReportCommand command) {

        String hostEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if(hostEmail != null) {
            User loggedInHost = hostRepository.findByEmail(hostEmail).orElseThrow();

            Report report = Report.builder()
                    .user(loggedInHost)
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
