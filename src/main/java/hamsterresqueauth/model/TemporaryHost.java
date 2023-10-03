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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "host")
    private Set<Report> reports;

    public void addReport(Report report){
        reports.add(report);
        report.setHost(this);
    }
}
