package phishy.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.TrainingUserinfoEntity;

import java.util.List;

public interface TrainingUserinfoRepository extends JpaRepository<TrainingUserinfoEntity, Long> {
    void deleteAllByTugId(Long tugId);
    List<TrainingUserinfoEntity> findAllByTugId(Long tugId);
}
