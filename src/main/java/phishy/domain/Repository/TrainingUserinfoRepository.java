package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingUserinfoEntity;

public interface TrainingUserinfoRepository extends JpaRepository<TrainingUserinfoEntity, Long> {
}
