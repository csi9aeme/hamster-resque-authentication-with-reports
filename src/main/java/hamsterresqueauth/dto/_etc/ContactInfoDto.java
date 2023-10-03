package hamsterresqueauth.dto._etc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoDto {

    private String email;

    private String phone;

    private String otherContactInfo;
}
