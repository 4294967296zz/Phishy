package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingProjectEntity;
import phishy.domain.Entity.TrainingSettingEntity;
import phishy.domain.Repository.TrainingGroupRepository;
import phishy.domain.Repository.TrainingProjectRepository;
import phishy.domain.Repository.TrainingSettingRepository;
import phishy.domain.Repository.TrainingUsergroupRepository;
import phishy.dto.TrainingProjectDto;
import phishy.dto.TrainingSettingDto;

import javax.persistence.EntityManager;
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
    private EntityManager em;


    @Transactional
    public void registerTRP(Map<String, String> datas, Long tugId, Long trgId, Long mfiId, Date trpStart, Date trpEnd, Integer tugCount) {

        TrainingSettingDto trainingSettingDto = new TrainingSettingDto();
        TrainingProjectDto trainingProjectDto = new TrainingProjectDto();
        TrainingProjectEntity trainingProjectEntity = trainingProjectDto.toEntity();
        TrainingSettingEntity trainingSettingEntity = trainingSettingDto.toEntity();

        trainingSettingEntity.setMfiId(mfiId);
        trainingSettingEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
        trainingSettingEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
        trainingSettingEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
        trainingSettingEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
        trainingSettingEntity.setMfi_nm(datas.get("mfi_nm"));
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
        trainingProjectEntity.setTrgId(trgId);
        trainingProjectEntity.setTrpNm(datas.get("trp_nm"));
        trainingProjectEntity.setTrpType(datas.get("trp_type"));
        trainingProjectEntity.setTrpInterval(Integer.parseInt(datas.get("trp_interval")));
        trainingProjectEntity.setTrpStatus("??????");
        trainingProjectEntity.setTrpStart(trpStart);
        trainingProjectEntity.setTrpEnd(trpEnd);
        trainingProjectEntity.setTrpContent(datas.get("trp_content"));
        trainingProjectEntity.setTrpSent(0);
        trainingProjectEntity.setTugCount(tugCount);
        trainingProjectRepository.save(trainingProjectEntity).getTrpId();
    }

    @Transactional
    public void updateTRP(Map<String, String> datas, Long trpId, Long trsId, Long tugId, Long trgId, Long mfiId, Date trpStart, Date trpEnd, Integer tugCount) {

        Optional<TrainingProjectEntity> trainingProjectWrapper = trainingProjectRepository.findById(trpId);
        TrainingProjectEntity trainingProjectEntity = trainingProjectWrapper.get();
        Optional<TrainingSettingEntity> trainingSettingEntityWrapper = trainingSettingRepository.findById(trsId);
        TrainingSettingEntity trainingSettingEntity = trainingSettingEntityWrapper.get();

        trainingSettingEntity.setMfiId(mfiId);
        trainingSettingEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
        trainingSettingEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
        trainingSettingEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
        trainingSettingEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
        trainingSettingEntity.setMfi_nm(datas.get("mfi_nm"));
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
        trainingSettingRepository.save(trainingSettingEntity);

        trainingProjectEntity.setTugId(tugId);
        trainingProjectEntity.setTrgId(trgId);
        trainingProjectEntity.setTrpNm(datas.get("trp_nm"));
        trainingProjectEntity.setTrpType(datas.get("trp_type"));
        trainingProjectEntity.setTrpInterval(Integer.parseInt(datas.get("trp_interval")));
        trainingProjectEntity.setTrpStatus("??????");
        trainingProjectEntity.setTrpStart(trpStart);
        trainingProjectEntity.setTrpEnd(trpEnd);
        trainingProjectEntity.setTrpContent(datas.get("trp_content"));
        trainingProjectEntity.setTugCount(tugCount);
        trainingProjectRepository.save(trainingProjectEntity);
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
                    .tugCount(trpEntity.getTugCount())
                    .trsId(trpEntity.getTrsId())
                    .trgId(trpEntity.getTrgId())
                    .build();
            trpDtoList.add(trpDto);
        }
        return trpDtoList;
    }

    @Transactional
    public List<TrainingProjectDto> getTRPsByTrgId(Long trgId) {
        List<TrainingProjectEntity> trpEntities = trainingProjectRepository.findAllByTrgId(trgId);
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
                    .tugCount(trpEntity.getTugCount())
                    .trsId(trpEntity.getTrsId())
                    .trgId(trpEntity.getTrgId())
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
                .trgId(trpEntity.getTrgId())
                .trpNm(trpEntity.getTrpNm())
                .trpContent(trpEntity.getTrpContent())
                .trpStart(trpEntity.getTrpStart())
                .trpEnd(trpEntity.getTrpEnd())
                .trpType(trpEntity.getTrpType())
                .trpStatus(trpEntity.getTrpStatus())
                .trpSent(trpEntity.getTrpSent())
                .tugCount(trpEntity.getTugCount())
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
                .mfi_nm(trsEntity.getMfi_nm())
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
    public Integer updateTRP(Long trpId, String msg) {
        Optional<TrainingProjectEntity> TRPEntityWrapper = trainingProjectRepository.findById(trpId);
        TrainingProjectEntity trainingProjectEntity = TRPEntityWrapper.get();
        trainingProjectEntity.setTrpStatus(msg);
        if(msg == "??????") {
            trainingProjectEntity.setTrpSent(trainingProjectEntity.getTrpSent() + 1);
        }
        trainingProjectRepository.save(trainingProjectEntity);

        return trainingProjectEntity.getTrpSent();
    }

    @Transactional
    public Integer getTRPSent(Long trpId) {
        Optional<TrainingProjectEntity> TRPEntityWrapper = trainingProjectRepository.findById(trpId);
        TrainingProjectEntity trainingProjectEntity = TRPEntityWrapper.get();
        return trainingProjectEntity.getTrpSent();
    }

    @Transactional
    public List<Long> getTotalTU(Long trgId) {
        String jpql = "SELECT SUM(tugCount) FROM TrainingProjectEntity WHERE trgId = :trgId";
        List<Long> totalResult = em.createQuery(jpql, Long.class)
                .setParameter("trgId",trgId)
                .getResultList();
        return totalResult;
    }

    @Transactional
    public void deleteTRP(Long trpId) {
        trainingProjectRepository.deleteById(trpId);
    }

}
