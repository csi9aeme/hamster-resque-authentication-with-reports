package hamsterresqueauth.dto.auth;

import hamsterresqueauth.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestCommand {

    @NotEmpty(message = "Firstname cannot be empty!")
    private String firstname;

    @NotEmpty(message = "Lastname cannot be empty!")
    private String lastname;

    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    private String password;

}
