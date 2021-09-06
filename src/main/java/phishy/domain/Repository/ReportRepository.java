package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.ReportEntity;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    Optional<ReportEntity> findByTrrId(Long trrId);
}
