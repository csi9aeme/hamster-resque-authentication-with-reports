package hamsterresqueauth.service;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.enums.Authorities;
import hamsterresqueauth.exception.UserNotLoggedInYetException;
import hamsterresqueauth.mapper.ReportMapper;
import hamsterresqueauth.mapper.TemporaryHostMapper;
import hamsterresqueauth.model.*;
import hamsterresqueauth.repository.ReportRepository;
import hamsterresqueauth.repository.TemporaryHostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    TemporaryHostRepository hostRepository;

    @Mock
    ReportRepository reportRepository;

    @Mock
    ReportMapper reportMapper;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Mock
    TemporaryHostMapper hostMapper;

    @InjectMocks
    ReportService reportService;

    String psw = "$2a$12$30kIOBsIYZpbZa3kazwbQuiHq5ISEpLguZ449pzn.vLqGTjdrJ0LS";
    TemporaryHost host;
    LoginInfo loginInfo;
    ContactInfo contactInfo;
    Address address;
    Report report;

    @BeforeEach
    void init() {
        loginInfo = new LoginInfo("valami@gmail.com", psw, Authorities.USER);
        address = new Address("1191", "Budapest", "Békés utca", "21", "2/7");
        contactInfo = new ContactInfo(loginInfo.getEmail(), "+36201112222", "skype");
        host = new TemporaryHost(1L, "Elemér", "Megyek", loginInfo, contactInfo, address, new HashSet<>());


    }

    @Test
    void testCreateReport() {
        String email = "valami@gmail.com";
        ReportDto reportDto = new ReportDto("Mütyürke", LocalDate.now(), 37, "Some information about the hamster.");
        CreateReportCommand command = new CreateReportCommand(host, "Hamster", LocalDate.now(), 10, "Test report");
        report = new Report(host, "Mütyürke", LocalDate.now(), 37, "Some information about the hamster.");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(email);

        SecurityContextHolder.setContext(securityContext);

        when(hostRepository.findByEmail(email)).thenReturn(Optional.of(host));

        when(reportMapper.toReportDto((Report) any())).thenReturn(reportDto);

        ReportDto result = reportService.createReport(command);

        assertNotNull(result);
        assertEquals(reportDto, result);

        verify(reportRepository, times(1)).save(any(Report.class));

    }

    @Test
    void testUserNotLoggedIn() {
        CreateReportCommand command = new CreateReportCommand(host, "Hamster", LocalDate.now(), 10, "Test report");

        String error = "User not logged in yet!";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(null);

        SecurityContextHolder.setContext(securityContext);

        assertThrows(UserNotLoggedInYetException.class, () -> reportService.createReport(command));

    }

}
