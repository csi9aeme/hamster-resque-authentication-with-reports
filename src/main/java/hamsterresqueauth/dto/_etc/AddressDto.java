package hamsterresqueauth.dto._etc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private String zip;

    private String city;

    private String street;

    private String houseNumber;

    private String otherAddressInfo;
}
