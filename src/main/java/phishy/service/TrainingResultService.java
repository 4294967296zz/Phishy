package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.MailLogEntity;
import phishy.domain.Entity.TrainingResultEntity;
import phishy.domain.Repository.MailLogRepository;
import phishy.domain.Repository.TrainingResultRepository;
import phishy.dto.MailLogDto;
import phishy.dto.TrainingResultDto;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class TrainingResultService {
    private TrainingResultRepository trainingResultRepository;
    private MailLogRepository mailLogRepository;

    @Transactional
    public Long registerTRR(String userNm, String userRank, String userId, Long trpId, Long trsId) {

        TrainingResultDto trainingResultDto = new TrainingResultDto();
        TrainingResultEntity trainingResultEntity = trainingResultDto.toEntity();
        MailLogDto mailLogDto = new MailLogDto();
        MailLogEntity mailLogEntity = mailLogDto.toEntity();

        trainingResultEntity.setTrpId(trpId);
        trainingResultEntity.setTrsId(trsId);
        trainingResultEntity.setUserNm(userNm);
        trainingResultEntity.setUserId(userId);
        trainingResultEntity.setUserRank(userRank);
        trainingResultEntity.setTrrOpen("N");
        trainingResultEntity.setTrrLink("N");
        trainingResultEntity.setTrrAttachClick("N");
        trainingResultEntity.setTrrAttachOpen("N");
        trainingResultEntity.setTrrPhishingclick("N");

       return trainingResultRepository.save(trainingResultEntity).getTrrId();
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
                    .build();
            TRRDtoList.add(TRRDto);
        }
        return TRRDtoList;
    }
}
