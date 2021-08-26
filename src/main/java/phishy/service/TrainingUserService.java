package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingUsergroupEntity;
import phishy.domain.Entity.TrainingUserinfoEntity;
import phishy.domain.Repository.TrainingUsergroupRepository;
import phishy.domain.Repository.TrainingUserinfoRepository;
import phishy.dto.TrainingUsergroupDto;
import phishy.dto.TrainingUserinfoDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@PersistenceContext
public class TrainingUserService {

    private EntityManager em;
    private TrainingUserinfoRepository trainingUserinfoRepository;
    private TrainingUsergroupRepository trainingUsergroupRepository;
    private UserService userService;

    @Transactional
    public List<TrainingUsergroupDto> getUsergroupList() {
        List<TrainingUsergroupEntity> TUGEntities = trainingUsergroupRepository.findAll();
        List<TrainingUsergroupDto> TUGDtoList = new ArrayList<>();

        for(TrainingUsergroupEntity TrainingUsergroupEntity : TUGEntities) {
            TrainingUsergroupDto TUGDto = TrainingUsergroupDto.builder()
                    .tugId(TrainingUsergroupEntity.getTugId())
                    .tugNm(TrainingUsergroupEntity.getTugNm())
                    .tugCount(TrainingUsergroupEntity.getTugCount())
                    .build();
            TUGDtoList.add(TUGDto);
        }
        return TUGDtoList;
    }

    @Transactional
    public TrainingUsergroupDto getTUG(Long tugId) {
        Optional<TrainingUsergroupEntity> TUGEntityWrapper = trainingUsergroupRepository.findById(tugId);
        TrainingUsergroupEntity trainingUsergroupEntity = TUGEntityWrapper.get();
        TrainingUsergroupDto trainingUsergroupDto = TrainingUsergroupDto.builder()
                .tugId(trainingUsergroupEntity.getTugId())
                .tugNm(trainingUsergroupEntity.getTugNm())
                .tugCount(trainingUsergroupEntity.getTugCount())
                .build();
        return trainingUsergroupDto;
    }

    @Transactional
    public TrainingUsergroupDto getTUGCounts(Long tugId) {
        Optional<TrainingUsergroupEntity> TUGEntityWrapper = trainingUsergroupRepository.findById(tugId);
        TrainingUsergroupEntity trainingUsergroupEntity = TUGEntityWrapper.get();
        TrainingUsergroupDto trainingUsergroupDto = TrainingUsergroupDto.builder()
                .tugCount(trainingUsergroupEntity.getTugCount())
                .build();
        return trainingUsergroupDto;
    }

    @Transactional
    public List<TrainingUserinfoDto> getTUI(Long tugId) {
        List<TrainingUserinfoEntity> TUIEntities = trainingUserinfoRepository.findAllByTugId(tugId);
        List<TrainingUserinfoDto> TUIDtoList = new ArrayList<>();

        for(TrainingUserinfoEntity TrainingUserinfoEntity : TUIEntities) {
            TrainingUserinfoDto TUGDto = TrainingUserinfoDto.builder()
                    .tugId(TrainingUserinfoEntity.getTugId())
                    .userId(TrainingUserinfoEntity.getUserId())
                    .deptCd(TrainingUserinfoEntity.getDeptCd())
                    .deptNm(TrainingUserinfoEntity.getDeptNm())
                    .build();
            TUIDtoList.add(TUGDto);
        }
        return TUIDtoList;
    }

    @Transactional
    public List<TrainingUserinfoDto> getUserinfoList() {
        List<TrainingUserinfoEntity> TUIEntities = trainingUserinfoRepository.findAll();
        List<TrainingUserinfoDto> TUIDtoList = new ArrayList<>();

        for(TrainingUserinfoEntity TrainingUserinfoEntity : TUIEntities) {
            TrainingUserinfoDto TUGDto = TrainingUserinfoDto.builder()
                    .tugId(TrainingUserinfoEntity.getTugId())
                    .userId(TrainingUserinfoEntity.getUserId())
                    .deptCd(TrainingUserinfoEntity.getDeptCd())
                    .deptNm(TrainingUserinfoEntity.getDeptNm())
                    .build();
            TUIDtoList.add(TUGDto);
        }
        return TUIDtoList;
    }

    @Transactional
    public Long registerTUG(String tugNm, Integer tugCount) {
        TrainingUsergroupDto trainingUsergroupDto = new TrainingUsergroupDto();
        TrainingUsergroupEntity trainingUsergroupEntity = trainingUsergroupDto.toEntity();
        trainingUsergroupEntity.setTugNm(tugNm);
        trainingUsergroupEntity.setTugCount(tugCount);
        return trainingUsergroupRepository.save(trainingUsergroupEntity).getTugId();
    }

    @Transactional
    public void updateTUG(Long tugId, String tugNm, Integer tugCount) {
        Optional<TrainingUsergroupEntity> TUGEntityWrapper = trainingUsergroupRepository.findById(tugId);
        TrainingUsergroupEntity trainingUsergroupEntity = TUGEntityWrapper.get();
        trainingUsergroupEntity.setTugNm(tugNm);
        trainingUsergroupEntity.setTugCount(tugCount);
        trainingUsergroupRepository.save(trainingUsergroupEntity);
    }

    @Transactional
    public void registerTUI(Long tugId, List<Long> userIds) {
        TrainingUserinfoDto trainingUserinfoDto = new TrainingUserinfoDto();

        for(Long str : userIds) {
            TrainingUserinfoEntity trainingUserinfoEntity = trainingUserinfoDto.toEntity();
            trainingUserinfoEntity.setTugId(tugId);
            trainingUserinfoEntity.setUserId(userService.getUser(str).getUserEmail());
            trainingUserinfoEntity.setDeptCd(userService.getUser(str).getDeptCd());
            trainingUserinfoEntity.setDeptNm(userService.getUser(str).getDeptNm());
            trainingUserinfoRepository.save(trainingUserinfoEntity).getTugId();
        }
    }

    @Transactional
    public void updateTUI(Long tugId, List<Long> userIds) {
        trainingUserinfoRepository.deleteAllByTugId(tugId);
        registerTUI(tugId, userIds);
    }

    @Transactional
    public void deleteTUG(Long tugId) {
        trainingUsergroupRepository.deleteById(tugId);
        trainingUserinfoRepository.deleteAllByTugId(tugId);
    }

    @Transactional
    public List<Object[]> getTUIdetails(Long tugId) {
        String jpql = "SELECT DISTINCT UE FROM TrainingUserinfoEntity TUI, UserEntity UE WHERE "
                +"TUI.userId = UE.userId AND TUI.tugId = :tugId";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("tugId",tugId)
                .getResultList();
        return totalResult;
    }

    @Transactional
    public List<String> getTUIemail(Long tugId) {
        String jpql = "SELECT TUI.userId FROM TrainingUserinfoEntity TUI WHERE TUI.tugId = :tugId";
        List<String> totalResult = em.createQuery(jpql, String.class)
                .setParameter("tugId",tugId)
                .getResultList();
        return totalResult;
    }

    @Transactional
    public List<TrainingUserinfoDto> getTUIemails(Long tugId) {
        List<TrainingUserinfoEntity> TUIEntities = trainingUserinfoRepository.findAllByTugId(tugId);
        List<TrainingUserinfoDto> TUIDtoList = new ArrayList<>();

        for(TrainingUserinfoEntity TrainingUserinfoEntity : TUIEntities) {
            TrainingUserinfoDto TUGDto = TrainingUserinfoDto.builder()
                    .userId(TrainingUserinfoEntity.getUserId())
                    .build();
            TUIDtoList.add(TUGDto);
        }
        return TUIDtoList;
    }

}
