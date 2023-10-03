package hamsterresqueauth.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "temporary_hosts")
public class TemporaryHost {

    @Id
    @GeneratedValue
    private long id;

    private String firstname;

    private String lastname;

    @Embedded
    private LoginInfo loginInfo; //email, psw

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "host")
    private Set<Report> reports;

    public void addReport(Report report){
        reports.add(report);
        report.setHost(this);
    }

    public TemporaryHost(String firstname, String lastname, LoginInfo loginInfo, ContactInfo contactInfo, Address address, Set<Report> reports) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.loginInfo = loginInfo;
        this.contactInfo = contactInfo;
        this.address = address;
        this.reports = reports;
    }
}
