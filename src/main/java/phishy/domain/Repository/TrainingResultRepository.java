package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingResultEntity;

public interface TrainingResultRepository extends JpaRepository<TrainingResultEntity, Long> {
}
