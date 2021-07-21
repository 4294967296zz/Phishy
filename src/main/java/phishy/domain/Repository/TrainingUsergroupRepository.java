package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingUsergroupEntity;

public interface TrainingUsergroupRepository extends JpaRepository<TrainingUsergroupEntity, Long> {
}
