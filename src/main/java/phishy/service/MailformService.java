package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.MailformEntity;
import phishy.domain.Repository.MailformRepository;
import phishy.dto.MailformDto;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MailformService {
    private MailformRepository mailformRepository;

    @Transactional
    public void registerMailform(Map<String, String> datas) {
        MailformDto mailformDto = new MailformDto();
        MailformEntity mailformEntity = mailformDto.toEntity();

        mailformEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
        mailformEntity.setMfi_nm(datas.get("mfi_nm"));
        mailformEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
        mailformEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
        mailformEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
        mailformRepository.save(mailformEntity).getMfi_id();
    }

    @Transactional
    public void updateMailform(Long mfi_id, Map<String, String> datas) {

        Optional<MailformEntity> mailformEntityWrapper = mailformRepository.findById(mfi_id);
        MailformEntity mailformEntity = mailformEntityWrapper.get();

        mailformEntity.setMfi_id(mfi_id);
        mailformEntity.setMfi_mail_nm(datas.get("mfi_mail_nm"));
        mailformEntity.setMfi_nm(datas.get("mfi_nm"));
        mailformEntity.setMfi_mail_addr(datas.get("mfi_mail_addr"));
        mailformEntity.setMfi_mail_title(datas.get("mfi_mail_title"));
        mailformEntity.setMfi_file_nm(datas.get("mfi_file_nm"));
        mailformRepository.save(mailformEntity);
    }
}
