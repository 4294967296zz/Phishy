package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.MailLogEntity;
import phishy.domain.Repository.MailLogRepository;
import phishy.dto.MailLogDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MailLogService {
    private MailLogRepository mailLogRepository;

    @Transactional
    public Long registerMailLog(Long trpId, Long trrId) {
        MailLogDto mailLogDto = new MailLogDto();
        MailLogEntity mailLogEntity = mailLogDto.toEntity();
        mailLogEntity.setTrpId(trpId);
        mailLogEntity.setTrrId(trrId);
        return mailLogRepository.save(mailLogEntity).getMlId();
    }

    @Transactional
    public List<MailLogDto> getMailLogs() {
        List<MailLogEntity> MLEntities = mailLogRepository.findAll();
        List<MailLogDto> MLDtoList = new ArrayList<>();

        for(MailLogEntity MailLogEntity : MLEntities) {
            MailLogDto MLDto = MailLogDto.builder()
                    .trpId(MailLogEntity.getTrpId())
                    .trrId(MailLogEntity.getTrrId())
                    .build();
            MLDtoList.add(MLDto);
        }
        return MLDtoList;
    }
}
