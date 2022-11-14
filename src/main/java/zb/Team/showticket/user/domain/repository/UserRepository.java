package zb.Team.showticket.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.user.domain.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByIdAndEmail(Long id, String email);
}
