package hamsterresqueauth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {

    @Column(insertable=false, updatable=false)
    private String email;

    private String phone;

    @Column(name = "other_contact_info")
    private String otherContactInfo;
}
