package hamsterresqueauth.dto.user;

import hamsterresqueauth.dto._etc.AddressDto;
import hamsterresqueauth.dto._etc.ContactInfoDto;
import hamsterresqueauth.dto.report.ReportDtoSimple;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryHostDto {

    private String firstname;

    private String lastname;

    private ContactInfoDto contactInfoDto;

    private AddressDto addressDto;

    private Set<ReportDtoSimple> reportDtos;

}
