package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingResultEntity;
import phishy.domain.Repository.TrainingResultRepository;
import phishy.dto.TrainingResultDto;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class TrainingResultService {
    private TrainingResultRepository trainingResultRepository;

    @Transactional
    public void registerTRR(Map<String, String> datas, Long tugId, Long mfiId, LocalDateTime trpStart, Date trpEnd) {

//        TrainingSettingDto trainingSettingDto = new TrainingSettingDto();
//        TrainingProjectDto trainingProjectDto = new TrainingProjectDto();
//        TrainingProjectEntity trainingProjectEntity = trainingProjectDto.toEntity();
//        TrainingSettingEntity trainingSettingEntity = trainingSettingDto.toEntity();
//
//        trainingSettingEntity.setMfiId(mfiId);
//        trainingSettingEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
//        trainingSettingEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
//        trainingSettingEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
//        trainingSettingEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
//        trainingSettingEntity.setTrsOpen(datas.get("trs_open"));
//        trainingSettingEntity.setTrsLink(datas.get("trs_link"));
//        trainingSettingEntity.setTrsAttachClick(datas.get("trs_attach_click"));
//        trainingSettingEntity.setTrsAttachOpen(datas.get("trs_attach_open"));
//        trainingSettingEntity.setTrsAttachNm(datas.get("trs_attach_nm"));
//        trainingSettingEntity.setTrsAttachType(datas.get("trs_attach_type"));
//        trainingSettingEntity.setTrsAttachSize(datas.get("trs_attach_size"));
//        trainingSettingEntity.setTrsPhishing(datas.get("trs_phishing"));
//        trainingSettingEntity.setTrsPhishingUrl(datas.get("trs_phishing_url"));
//        trainingSettingEntity.setTrsPhishingContent(datas.get("trs_phishing_content"));
//
//        Long trs_id = trainingSettingRepository.save(trainingSettingEntity).getTrsId();
//
//        trainingProjectEntity.setTrsId(trs_id);
//        trainingProjectEntity.setTugId(tugId);
//        trainingProjectEntity.setTrpNm(datas.get("trp_nm"));
//        trainingProjectEntity.setTrpType(datas.get("trp_type"));
//        trainingProjectEntity.setTrpInterval(Integer.parseInt(datas.get("trp_interval")));
//        trainingProjectEntity.setTrpStatus("대기");
//        trainingProjectEntity.setTrpStart(trpStart);
//        trainingProjectEntity.setTrpEnd(trpEnd);
//        trainingProjectEntity.setTrpContent(datas.get("trp_content"));
//        trainingProjectEntity.setTrpSent(0);
//        trainingProjectRepository.save(trainingProjectEntity).getTrpId();
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
                    .build();
            TRRDtoList.add(TRRDto);
        }
        return TRRDtoList;
    }
}
