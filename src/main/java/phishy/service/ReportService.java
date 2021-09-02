package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.ReportEntity;
import phishy.domain.Repository.ReportRepository;
import phishy.dto.ReportDto;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportService {
    private ReportRepository reportRepository;

    @Transactional
    public Long registerReport(Long trrId, Map<String, String> datas, Date rp_rcpt_date, Date rp_open_date) {
        ReportDto reportDto = new ReportDto();
        ReportEntity reportEntity = reportDto.toEntity();
        reportEntity.setTrrId(trrId);
        reportEntity.setRpUsername(datas.get("rp_username"));
        reportEntity.setRpDeptnm(datas.get("rp_deptnm"));
        reportEntity.setRpIp(datas.get("rp_ip"));
        reportEntity.setRpMailTitle(datas.get("rp_mail_title"));
        reportEntity.setRpMailFrom(datas.get("rp_mail_from"));
        reportEntity.setRpMailAddr(datas.get("rp_mail_addr"));
        reportEntity.setRpRcptDate(rp_rcpt_date);
        reportEntity.setRpOpenDate(rp_open_date);
        return reportRepository.save(reportEntity).getRpId();
    }

}
