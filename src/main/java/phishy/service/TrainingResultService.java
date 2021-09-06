package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingResultEntity;
import phishy.domain.Repository.TrainingResultRepository;
import phishy.dto.TrainingResultDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class TrainingResultService {
    private EntityManager em;
    private TrainingResultRepository trainingResultRepository;

    @Transactional
    public Long registerTRR(String userNm, String userRank, String userId, Long trpId, Long trsId, Long tugId, Integer trpSent) {

        TrainingResultDto trainingResultDto = new TrainingResultDto();
        TrainingResultEntity trainingResultEntity = trainingResultDto.toEntity();

        trainingResultEntity.setTrpId(trpId);
        trainingResultEntity.setTrsId(trsId);
        trainingResultEntity.setTugId(tugId);
        trainingResultEntity.setTrpSent(trpSent+1);
        trainingResultEntity.setUserNm(userNm);
        trainingResultEntity.setUserId(userId);
        trainingResultEntity.setUserRank(userRank);
        trainingResultEntity.setTrrOpen("N");
        trainingResultEntity.setTrrLink("N");
        trainingResultEntity.setTrrAttachClick("N");
        trainingResultEntity.setTrrAttachOpen("N");
        trainingResultEntity.setTrrPhishingclick("N");
        trainingResultEntity.setTrrReport("N");
        trainingResultEntity.setTrrReturnIp(" ");
        trainingResultEntity.setTrrReturnUserinfo(" ");
        trainingResultEntity.setTrrPhishingContent(" ");

       return trainingResultRepository.save(trainingResultEntity).getTrrId();
    }

    @Transactional
    public void updateMailOpen(Long trrId, String returnIP, String returnInfo) {
        Optional<TrainingResultEntity> TRREntityWrapper = trainingResultRepository.findById(trrId);
        TrainingResultEntity TRREntity = TRREntityWrapper.get();
        TRREntity.setTrrOpen("Y");
        TRREntity.setTrrOpenDate(LocalDateTime.now());
        TRREntity.setTrrReturnIp(returnIP);
        TRREntity.setTrrReturnUserinfo(returnInfo);
        trainingResultRepository.save(TRREntity);
    }

    @Transactional
    public void updateLinkClicked(Long trrId) {
        Optional<TrainingResultEntity> TRREntityWrapper = trainingResultRepository.findById(trrId);
        TrainingResultEntity TRREntity = TRREntityWrapper.get();
        TRREntity.setTrrLink("Y");
        TRREntity.setTrrLinkDate(LocalDateTime.now());
        trainingResultRepository.save(TRREntity);
    }

    @Transactional
    public void updateAttachDownload(Long trrId) {
        Optional<TrainingResultEntity> TRREntityWrapper = trainingResultRepository.findById(trrId);
        TrainingResultEntity TRREntity = TRREntityWrapper.get();
        TRREntity.setTrrAttachClick("Y");
        TRREntity.setTrrAttachClickDate(LocalDateTime.now());
        trainingResultRepository.save(TRREntity);
    }

    @Transactional
    public void updateAttachOpen(Long trrId) {
        Optional<TrainingResultEntity> TRREntityWrapper = trainingResultRepository.findById(trrId);
        TrainingResultEntity TRREntity = TRREntityWrapper.get();
        TRREntity.setTrrAttachOpen("Y");
        TRREntity.setTrrAttachOpenDate(LocalDateTime.now());
        trainingResultRepository.save(TRREntity);
    }

    @Transactional
    public void updateReport(Long trrId) {
        Optional<TrainingResultEntity> TRREntityWrapper = trainingResultRepository.findById(trrId);
        TrainingResultEntity TRREntity = TRREntityWrapper.get();
        TRREntity.setTrrReport("Y");
        trainingResultRepository.save(TRREntity);
    }

    @Transactional
    public List<TrainingResultDto> getTRRs() {
        List<TrainingResultEntity> trrEntities = trainingResultRepository.findAll(Sort.by(Sort.Direction.DESC, "trrId"));
        List<TrainingResultDto> trrDtoList = new ArrayList<>();

        for(TrainingResultEntity trrEntity : trrEntities) {
            TrainingResultDto trrDto = TrainingResultDto.builder()
                    .trrId(trrEntity.getTrrId())
                    .trpId(trrEntity.getTrpId())
                    .tugId(trrEntity.getTugId())
                    .trpSent(trrEntity.getTrpSent())
                    .trsId(trrEntity.getTrsId())
                    .userId(trrEntity.getUserId())
                    .userRank(trrEntity.getUserRank())
                    .userNm(trrEntity.getUserNm())
                    .trrOpen(trrEntity.getTrrOpen())
                    .trrOpenDate(trrEntity.getTrrOpenDate())
                    .trrLink(trrEntity.getTrrLink())
                    .trrLinkDate(trrEntity.getTrrLinkDate())
                    .trrAttachClick(trrEntity.getTrrAttachClick())
                    .trrAttachClickDate(trrEntity.getTrrAttachClickDate())
                    .build();
            trrDtoList.add(trrDto);
        }
        return trrDtoList;
    }

    @Transactional
    public List<TrainingResultDto> getTRR(Long trpId) {
        List<TrainingResultEntity> TRREntities = trainingResultRepository.findAllByTrpId(trpId);
        List<TrainingResultDto> TRRDtoList = new ArrayList<>();

        for(TrainingResultEntity TrainingResultEntity : TRREntities) {
            TrainingResultDto TRRDto = TrainingResultDto.builder()
                    .trrId(TrainingResultEntity.getTrrId())
                    .trpId(TrainingResultEntity.getTrpId())
                    .tugId(TrainingResultEntity.getTugId())
                    .trpSent(TrainingResultEntity.getTrpSent())
                    .trsId(TrainingResultEntity.getTrsId())
                    .userId(TrainingResultEntity.getUserId())
                    .userRank(TrainingResultEntity.getUserRank())
                    .userNm(TrainingResultEntity.getUserNm())
                    .trrOpen(TrainingResultEntity.getTrrOpen())
                    .trrOpenDate(TrainingResultEntity.getTrrOpenDate())
                    .trrLink(TrainingResultEntity.getTrrLink())
                    .trrLinkDate(TrainingResultEntity.getTrrLinkDate())
                    .trrAttachClick(TrainingResultEntity.getTrrAttachClick())
                    .trrAttachClickDate(TrainingResultEntity.getTrrAttachClickDate())
                    .trrAttachOpen(TrainingResultEntity.getTrrAttachOpen())
                    .trrAttachOpenDate(TrainingResultEntity.getTrrAttachOpenDate())
                    .trrPhishingclick(TrainingResultEntity.getTrrPhishingclick())
                    .trrPhishingclickDate(TrainingResultEntity.getTrrPhishingclickDate())
                    .trrPhishingContent(TrainingResultEntity.getTrrPhishingContent())
                    .trrReturnIp(TrainingResultEntity.getTrrReturnIp())
                    .trrReturnUserinfo(TrainingResultEntity.getTrrReturnUserinfo())
                    .trrReport(TrainingResultEntity.getTrrReport())
                    .build();
            TRRDtoList.add(TRRDto);
        }
        return TRRDtoList;
    }

    @Transactional
    public List<Object[]> getTRRbySent(Long trpId, Integer sent) {
        String jpql = "SELECT DISTINCT TRR FROM TrainingResultEntity TRR WHERE "
                +"TRR.trpId = :trpId AND TRR.trpSent = :sent";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("trpId",trpId)
                .setParameter("sent",sent)
                .getResultList();
        return totalResult;
    }
}
