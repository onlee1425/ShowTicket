package zb.Team.showticket.provider.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.provider.domain.entity.Provider;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {
    Optional<Provider> findByEmail(String email);
}
