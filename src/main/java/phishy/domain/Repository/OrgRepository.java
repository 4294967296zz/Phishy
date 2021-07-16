package phishy.domain.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phishy.domain.Entity.OrgEntity;

public interface OrgRepository extends JpaRepository<OrgEntity, Long> {
}
