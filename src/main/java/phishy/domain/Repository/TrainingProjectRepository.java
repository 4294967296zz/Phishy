package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingProjectEntity;

import java.util.List;

public interface TrainingProjectRepository extends JpaRepository<TrainingProjectEntity, Long> {
    List<TrainingProjectEntity> findAllByTrgId(Long TrgId);
}

