package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.UserEntity;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findAllByUserEmail(String userEmail);
}
