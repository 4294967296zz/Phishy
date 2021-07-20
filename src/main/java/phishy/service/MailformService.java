package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.MailformEntity;
import phishy.domain.Repository.MailformRepository;
import phishy.dto.MailformDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public List<MailformDto> getMailformlist() {
        List<MailformEntity> mailformEntities = mailformRepository.findAll();
        List<MailformDto> mailformDtoList = new ArrayList<>();

        for(MailformEntity mailformEntity : mailformEntities) {
            MailformDto mailformDto = MailformDto.builder()
                    .mfi_id(mailformEntity.getMfi_id())
                    .mfi_nm(mailformEntity.getMfi_nm())
                    .mfi_mail_addr(mailformEntity.getMfi_mail_addr())
                    .mfi_mail_nm(mailformEntity.getMfi_mail_nm())
                    .mfi_mail_title(mailformEntity.getMfi_mail_title())
                    .mfi_file_nm(mailformEntity.getMfi_file_nm())
                    .build();
            mailformDtoList.add(mailformDto);
        }
        return mailformDtoList;
    }

    @Transactional
    public MailformDto getMailform(Long mfi_id) {
        Optional<MailformEntity> mailformEntityWrapper = mailformRepository.findById(mfi_id);
        MailformEntity mailformEntity = mailformEntityWrapper.get();

            MailformDto mailformDto = MailformDto.builder()
                    .mfi_id(mailformEntity.getMfi_id())
                    .mfi_nm(mailformEntity.getMfi_nm())
                    .mfi_mail_addr(mailformEntity.getMfi_mail_addr())
                    .mfi_mail_nm(mailformEntity.getMfi_mail_nm())
                    .mfi_mail_title(mailformEntity.getMfi_mail_title())
                    .mfi_file_nm(mailformEntity.getMfi_file_nm())
                    .build();
        return mailformDto;
    }

    @Transactional
    public void deleteMailform(Long mfi_id) {
        mailformRepository.deleteById(mfi_id);
    }

}
