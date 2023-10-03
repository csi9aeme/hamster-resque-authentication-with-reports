package hamsterresqueauth.repository;

import hamsterresqueauth.model.TemporaryHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TemporaryHostRepository extends JpaRepository<TemporaryHost, Long> {

    @Query("select host from TemporaryHost host where host.loginInfo.email = :email")
    Optional<TemporaryHost> findByEmail(@Param("email") String email);
}
