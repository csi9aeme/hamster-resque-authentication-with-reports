package hamsterresqueauth.repository;


import hamsterresqueauth.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
