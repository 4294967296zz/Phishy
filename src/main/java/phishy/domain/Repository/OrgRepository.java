package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.OrgEntity;

import java.util.Optional;

public interface OrgRepository extends JpaRepository<OrgEntity, Long> {
    Optional<OrgEntity> findByDeptCd(String deptCd);
}
