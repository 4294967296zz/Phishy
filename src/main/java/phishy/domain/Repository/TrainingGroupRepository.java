package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingGroupEntity;

public interface TrainingGroupRepository extends JpaRepository<TrainingGroupEntity, Long> {
}
