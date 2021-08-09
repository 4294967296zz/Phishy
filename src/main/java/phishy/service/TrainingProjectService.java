package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingProjectEntity;
import phishy.domain.Entity.TrainingSettingEntity;
import phishy.domain.Repository.TrainingProjectRepository;
import phishy.domain.Repository.TrainingSettingRepository;
import phishy.dto.TrainingProjectDto;
import phishy.dto.TrainingSettingDto;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainingProjectService {
    private TrainingProjectRepository trainingProjectRepository;
    private TrainingSettingRepository trainingSettingRepository;

    @Transactional
    public void registerTRP(Map<String, String> datas, Long tugId, Long mfiId, Date trpStart, Date trpEnd) {

        TrainingSettingDto trainingSettingDto = new TrainingSettingDto();
        TrainingProjectDto trainingProjectDto = new TrainingProjectDto();
        TrainingProjectEntity trainingProjectEntity = trainingProjectDto.toEntity();
        TrainingSettingEntity trainingSettingEntity = trainingSettingDto.toEntity();

        trainingSettingEntity.setMfiId(mfiId);
        trainingSettingEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
        trainingSettingEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
        trainingSettingEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
        trainingSettingEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
        trainingSettingEntity.setTrsOpen(datas.get("trs_open"));
        trainingSettingEntity.setTrsLink(datas.get("trs_link"));
        trainingSettingEntity.setTrsAttachClick(datas.get("trs_attach_click"));
        trainingSettingEntity.setTrsAttachOpen(datas.get("trs_attach_open"));
        trainingSettingEntity.setTrsAttachNm(datas.get("trs_attach_nm"));
        trainingSettingEntity.setTrsAttachType(datas.get("trs_attach_type"));
        trainingSettingEntity.setTrsAttachSize(datas.get("trs_attach_size"));
        trainingSettingEntity.setTrsPhishing(datas.get("trs_phishing"));
        trainingSettingEntity.setTrsPhishingUrl(datas.get("trs_phishing_url"));
        trainingSettingEntity.setTrsPhishingContent(datas.get("trs_phishing_content"));

        Long trs_id = trainingSettingRepository.save(trainingSettingEntity).getTrsId();

        trainingProjectEntity.setTrsId(trs_id);
        trainingProjectEntity.setTugId(tugId);
        trainingProjectEntity.setTrpNm(datas.get("trp_nm"));
        trainingProjectEntity.setTrpType(datas.get("trp_type"));
        trainingProjectEntity.setTrpInterval(Integer.parseInt(datas.get("trp_interval")));
        trainingProjectEntity.setTrpStatus("대기");
        trainingProjectEntity.setTrpStart(trpStart);
        trainingProjectEntity.setTrpEnd(trpEnd);
        trainingProjectEntity.setTrpContent(datas.get("trp_content"));
        trainingProjectEntity.setTrpSent(0);
        trainingProjectRepository.save(trainingProjectEntity).getTrpId();
    }

    @Transactional
    public List<TrainingProjectDto> getTRPs() {
        List<TrainingProjectEntity> trpEntities = trainingProjectRepository.findAll(Sort.by(Sort.Direction.DESC, "trpId"));
        List<TrainingProjectDto> trpDtoList = new ArrayList<>();

        for(TrainingProjectEntity trpEntity : trpEntities) {
            TrainingProjectDto trpDto = TrainingProjectDto.builder()
                    .trpId(trpEntity.getTrpId())
                    .trpNm(trpEntity.getTrpNm())
                    .trpContent(trpEntity.getTrpContent())
                    .trpStart(trpEntity.getTrpStart())
                    .trpEnd(trpEntity.getTrpEnd())
                    .trpType(trpEntity.getTrpType())
                    .tugId(trpEntity.getTugId())
                    .trpStatus(trpEntity.getTrpStatus())
                    .trpSent(trpEntity.getTrpSent())
                    .trsId(trpEntity.getTrsId())
                    .build();
            trpDtoList.add(trpDto);
        }
        return trpDtoList;
    }

    @Transactional
    public List<TrainingSettingDto> getTRSs() {
        List<TrainingSettingEntity> trsEntities = trainingSettingRepository.findAll();
        List<TrainingSettingDto> trsDtoList = new ArrayList<>();

        for(TrainingSettingEntity trsEntity : trsEntities) {
            TrainingSettingDto trsDto = TrainingSettingDto.builder()
                    .trsId(trsEntity.getTrsId())
                    .trsOpen(trsEntity.getTrsOpen())
                    .trsLink(trsEntity.getTrsLink())
                    .trsAttachNm(trsEntity.getTrsAttachNm())
                    .trsAttachClick(trsEntity.getTrsAttachClick())
                    .trsAttachOpen(trsEntity.getTrsAttachOpen())
                    .trsAttachType(trsEntity.getTrsAttachType())
                    .trsAttachSize(trsEntity.getTrsAttachSize())
                    .trsPhishing(trsEntity.getTrsPhishing())
                    .trsPhishingUrl(trsEntity.getTrsPhishingUrl())
                    .trsPhishingContent(trsEntity.getTrsPhishingContent())
                    .build();
            trsDtoList.add(trsDto);
        }
        return trsDtoList;
    }

    @Transactional
    public TrainingProjectDto getTRP(Long trpId) {
        Optional<TrainingProjectEntity> trpEntityWrapper = trainingProjectRepository.findById(trpId);
        TrainingProjectEntity trpEntity = trpEntityWrapper.get();
        TrainingProjectDto trpDto = TrainingProjectDto.builder()
                .tugId(trpEntity.getTugId())
                .trpNm(trpEntity.getTrpNm())
                .trpContent(trpEntity.getTrpContent())
                .trpStart(trpEntity.getTrpStart())
                .trpEnd(trpEntity.getTrpEnd())
                .trpType(trpEntity.getTrpType())
                .trpStatus(trpEntity.getTrpStatus())
                .trpSent(trpEntity.getTrpSent())
                .trpInterval(trpEntity.getTrpInterval())
                .build();
        return trpDto;
    }

    @Transactional
    public TrainingSettingDto getTRS(Long trsId) {
        Optional<TrainingSettingEntity> trsEntityWrapper = trainingSettingRepository.findById(trsId);
        TrainingSettingEntity trsEntity = trsEntityWrapper.get();
        TrainingSettingDto trsDto = TrainingSettingDto.builder()
                .trsId(trsEntity.getTrsId())
                .mfiId(trsEntity.getMfiId())
                .mfi_file_nm(trsEntity.getMfi_file_nm())
                .mfi_mail_addr(trsEntity.getMfi_mail_addr())
                .mfi_mail_nm(trsEntity.getMfi_mail_nm())
                .mfi_mail_title(trsEntity.getMfi_mail_title())
                .trsOpen(trsEntity.getTrsOpen())
                .trsLink(trsEntity.getTrsLink())
                .trsAttachNm(trsEntity.getTrsAttachNm())
                .trsAttachClick(trsEntity.getTrsAttachClick())
                .trsAttachOpen(trsEntity.getTrsAttachOpen())
                .trsAttachType(trsEntity.getTrsAttachType())
                .trsAttachSize(trsEntity.getTrsAttachSize())
                .trsPhishing(trsEntity.getTrsPhishing())
                .trsPhishingUrl(trsEntity.getTrsPhishingUrl())
                .trsPhishingContent(trsEntity.getTrsPhishingContent())
                .build();
        return trsDto;
    }

    @Transactional
    public void updateTRP(Long trpId, String msg) {
        Optional<TrainingProjectEntity> TRPEntityWrapper = trainingProjectRepository.findById(trpId);
        TrainingProjectEntity trainingProjectEntity = TRPEntityWrapper.get();
        trainingProjectEntity.setTrpStatus(msg);
        if(msg == "완료") {
            trainingProjectEntity.setTrpSent(trainingProjectEntity.getTrpSent() + 1);
        }
        trainingProjectRepository.save(trainingProjectEntity);
    }

}
