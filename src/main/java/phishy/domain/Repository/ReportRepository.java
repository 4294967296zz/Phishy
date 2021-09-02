package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
