package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.MailLogEntity;

public interface MailLogRepository extends JpaRepository<MailLogEntity, Long> {
}
