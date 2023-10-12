package hamsterresqueauth.model._embedded;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LoginInfo  {

    @Column(unique = true)
    private String email;

    private String password;


}
