package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingProjectEntity;
import phishy.domain.Entity.TrainingSettingEntity;
import phishy.domain.Repository.TrainingProjectRepository;
import phishy.domain.Repository.TrainingSettingRepository;
import phishy.dto.TrainingProjectDto;
import phishy.dto.TrainingSettingDto;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Map;

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
        trainingProjectEntity.setTrpInterval(Integer.parseInt(datas.get("trp_interval")));
        trainingProjectEntity.setTrpStatus("대기");
        trainingProjectEntity.setTrpStart(trpStart);
        trainingProjectEntity.setTrpEnd(trpEnd);
        trainingProjectEntity.setTrpContent(datas.get("trp_content"));
        trainingProjectRepository.save(trainingProjectEntity).getTrpId();
    }

}
