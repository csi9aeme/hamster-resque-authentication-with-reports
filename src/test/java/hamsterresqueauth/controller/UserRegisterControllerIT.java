package hamsterresqueauth.controller;

import hamsterresqueauth.dto.user.UserDto;
import hamsterresqueauth.dto.user.UserRegisterCommand;
import hamsterresqueauth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserRegisterControllerIT {

    @Autowired
    WebTestClient webClient;

    @Autowired
    ControllerForTest controller;

    @Autowired
    UserRepository repository;

    @Test
    void testRegisterUserValid() {
        UserRegisterCommand registerCommand = new UserRegisterCommand(
                "Elemér", "Megyek", "megyekelemer@gmail.com", "1234",
                "+36201234567", "", "1191", "Budapest", "Fő utca",
                "38", "2/4");

        UserDto result = webClient.post().uri("/api/test/save")
                .bodyValue(registerCommand)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CREATED)
                .expectBody(UserDto.class).returnResult().getResponseBody();

        assertThat(result).isNotNull();
        assertThat(result.getFirstname()).isEqualTo("Elemér");
        assertThat(result.getAddressDto()).isNotNull();
        assertThat(result.getAddressDto().getCity()).isEqualTo("Budapest");
        assertThat(result.getLoginInfoDto().getEmail()).isEqualTo("megyekelemer@gmail.com");
        assertThat(result.getLoginInfoDto().getPassword()).isNotEqualTo("1234");


    }


}
