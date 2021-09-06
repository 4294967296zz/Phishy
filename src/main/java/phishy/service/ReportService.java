package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.ReportEntity;
import phishy.domain.Repository.ReportRepository;
import phishy.dto.ReportDto;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportService {
    private ReportRepository reportRepository;

    @Transactional
    public Long registerReport(Long trrId, Map<String, String> datas, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime rp_rcpt_date, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime rp_open_date) {
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

    @Transactional
    public ReportDto getReport(Long trrId) {
        Optional<ReportEntity> reportEntityWrapper = reportRepository.findByTrrId(trrId);
        ReportEntity reportEntity = reportEntityWrapper.get();

        ReportDto reportDTO = ReportDto.builder()
                .rpId(reportEntity.getRpId())
                .rpUsername(reportEntity.getRpUsername())
                .rpDeptnm(reportEntity.getRpDeptnm())
                .rpMailFrom(reportEntity.getRpMailFrom())
                .rpMailAddr(reportEntity.getRpMailAddr())
                .rpMailTitle(reportEntity.getRpMailTitle())
                .rpIp(reportEntity.getRpIp())
                .rpRcptDate(reportEntity.getRpRcptDate())
                .rpOpenDate(reportEntity.getRpRcptDate())
                .rpContents(reportEntity.getRpContents())
                .build();

        return reportDTO;
    }

}
