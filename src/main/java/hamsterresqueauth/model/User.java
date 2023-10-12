package hamsterresqueauth.model;

import hamsterresqueauth.model._embedded.Address;
import hamsterresqueauth.model._embedded.ContactInfo;
import hamsterresqueauth.model._embedded.LoginInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;

    private String lastname;

    @Embedded
    private LoginInfo loginInfo; //email, psw

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private Set<Report> reports;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_authorities",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="AUTHORITY_ID", referencedColumnName="ID")})
    private List<Authority> authorities = new ArrayList<>();


    public void addReport(Report report){
        reports.add(report);
        report.setUser(this);
    }

    public User(String firstname, String lastname, LoginInfo loginInfo, ContactInfo contactInfo,
                Address address, Set<Report> reports, List<Authority> authorities) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.loginInfo = loginInfo;
        this.contactInfo = contactInfo;
        this.address = address;
        this.reports = reports;
        this.authorities = authorities;
    }
}
