package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
