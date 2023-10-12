package hamsterresqueauth.controller;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.dto.user.UserDto;
import hamsterresqueauth.dto.user.UserRegisterCommand;
import hamsterresqueauth.model.*;
import hamsterresqueauth.model._embedded.Address;
import hamsterresqueauth.model._embedded.ContactInfo;
import hamsterresqueauth.model._embedded.LoginInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerIT {

    @Autowired
    WebTestClient webClient;

    UserRegisterCommand registerCommand;
    User host;
    UserDto hostDto;
    LoginInfo loginInfo;
    ContactInfo contactInfo;
    Address address;


    CreateReportCommand command;
    String email = "valami@gmail.com";
    ReportDto reportDto = new ReportDto("Mütyürke", LocalDate.now(), 37, "Some information about the hamster.");


    @BeforeEach
    void initUser() {
        registerCommand = new UserRegisterCommand(
                "Elemér", "Megyek", "valami@gmail.com", "1111", "+36201112222", "skype",
                "1191", "Budapest", "Békés utca", "21", "2/7");

        hostDto = webClient.post().uri("/api/auth/register")
                .bodyValue(registerCommand)
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(UserDto.class).returnResult().getResponseBody();


    }

    @Test
    void testCreateHost() {
        hostDto = webClient.post().uri("/api/auth/register")
                .bodyValue(registerCommand)
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(UserDto.class).returnResult().getResponseBody();

        assertNotNull(hostDto);
    }
}
