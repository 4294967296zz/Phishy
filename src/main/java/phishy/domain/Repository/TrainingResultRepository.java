package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingResultEntity;

import java.util.List;
import java.util.Optional;

public interface TrainingResultRepository extends JpaRepository<TrainingResultEntity, Long> {
    List<TrainingResultEntity> findAllByTrpId(Long trpId);
}
