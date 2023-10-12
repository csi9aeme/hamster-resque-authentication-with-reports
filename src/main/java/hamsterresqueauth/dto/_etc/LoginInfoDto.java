package hamsterresqueauth.dto._etc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfoDto {

    private String email;

    private String password;

    public LoginInfoDto(String password) {
        this.password = password;
    }
}
