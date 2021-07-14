package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.MailformEntity;

public interface MailformRepository extends JpaRepository<MailformEntity, Long> {
}
