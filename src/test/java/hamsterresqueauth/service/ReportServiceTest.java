package hamsterresqueauth.service;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.exception.UserNotLoggedInYetException;
import hamsterresqueauth.mapper.ReportMapper;
import hamsterresqueauth.mapper.UserMapper;
import hamsterresqueauth.model.*;
import hamsterresqueauth.model._embedded.Address;
import hamsterresqueauth.model._embedded.ContactInfo;
import hamsterresqueauth.model._embedded.LoginInfo;
import hamsterresqueauth.repository.ReportRepository;
import hamsterresqueauth.repository.UserRepository;
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
import java.util.ArrayList;
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
    UserRepository hostRepository;

    @Mock
    ReportRepository reportRepository;

    @Mock
    ReportMapper reportMapper;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Mock
    UserMapper hostMapper;

    @InjectMocks
    ReportService reportService;

    String psw = "$2a$12$30kIOBsIYZpbZa3kazwbQuiHq5ISEpLguZ449pzn.vLqGTjdrJ0LS";
    User user;
    LoginInfo loginInfo;
    ContactInfo contactInfo;
    Address address;
    Report report;

    @BeforeEach
    void init() {
        loginInfo = new LoginInfo("valami@gmail.com", psw);
        address = new Address("1191", "Budapest", "Békés utca", "21", "2/7");
        contactInfo = new ContactInfo(loginInfo.getEmail(), "+36201112222", "skype");
        user = new User(1L, "Elemér", "Megyek", loginInfo, contactInfo, address, new HashSet<>(), new ArrayList<>());


    }

    @Test
    void testCreateReport() {
        String email = "valami@gmail.com";
        ReportDto reportDto = new ReportDto("Mütyürke", LocalDate.now(), 37, "Some information about the hamster.");
        CreateReportCommand command = new CreateReportCommand(user, "Hamster", LocalDate.now(), 10, "Test report");
        report = new Report(user, "Mütyürke", LocalDate.now(), 37, "Some information about the hamster.");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(email);

        SecurityContextHolder.setContext(securityContext);

        when(hostRepository.findByEmail(email)).thenReturn(Optional.of(user));

        when(reportMapper.toReportDto((Report) any())).thenReturn(reportDto);

        ReportDto result = reportService.createReport(command);

        assertNotNull(result);
        assertEquals(reportDto, result);

        verify(reportRepository, times(1)).save(any(Report.class));

    }

    @Test
    void testUserNotLoggedIn() {
        CreateReportCommand command = new CreateReportCommand(user, "Hamster", LocalDate.now(), 10, "Test report");

        String error = "User not logged in yet!";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(null);

        SecurityContextHolder.setContext(securityContext);

        assertThrows(UserNotLoggedInYetException.class, () -> reportService.createReport(command));

    }

}
