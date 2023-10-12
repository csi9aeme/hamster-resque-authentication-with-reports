package hamsterresqueauth.dto.user;

import hamsterresqueauth.dto._etc.AddressDto;
import hamsterresqueauth.dto._etc.ContactInfoDto;
import hamsterresqueauth.dto._etc.LoginInfoDto;
import hamsterresqueauth.dto.report.ReportDto;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstname;

    private String lastname;

    private LoginInfoDto loginInfoDto;

    private ContactInfoDto contactInfoDto;

    private AddressDto addressDto;

    private Set<ReportDto> reportDtos;

}
