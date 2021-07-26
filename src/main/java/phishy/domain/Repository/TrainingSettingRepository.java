package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingSettingEntity;

public interface TrainingSettingRepository extends JpaRepository<TrainingSettingEntity, Long> {
}
