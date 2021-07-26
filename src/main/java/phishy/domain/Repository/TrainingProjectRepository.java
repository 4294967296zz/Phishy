package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingProjectEntity;

public interface TrainingProjectRepository extends JpaRepository<TrainingProjectEntity, Long> {
}
