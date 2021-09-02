package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.ReportEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReportDto {

    private Long rpId;
    private Long trrId;
    private String rpUsername;
    private String rpDeptnm;
    private String rpIp;
    private String rpMailTitle;
    private String rpMailFrom;
    private String rpMailAddr;
    private Date rpRcptDate;
    private Date rpOpenDate;
    private String rpContents;

    public ReportEntity toEntity() {
        ReportEntity orgEntity = ReportEntity.builder()
                .rpId(rpId)
                .trrId(trrId)
                .rpUsername(rpUsername)
                .rpDeptnm(rpDeptnm)
                .rpIp(rpIp)
                .rpMailTitle(rpMailTitle)
                .rpMailFrom(rpMailFrom)
                .rpMailAddr(rpMailAddr)
                .rpRcptDate(rpRcptDate)
                .rpOpenDate(rpOpenDate)
                .rpContents(rpContents)
                .build();
        return orgEntity;
    }

    @Builder
    public ReportDto(Long rpId,Long trrId,String rpUsername,String rpDeptnm,String rpIp,
                        String rpMailTitle,String rpMailFrom,String rpMailAddr,Date rpRcptDate,
                        Date rpOpenDate,String rpContents) {
        this.rpId = rpId;
        this.trrId = trrId;
        this.rpUsername = rpUsername;
        this.rpDeptnm = rpDeptnm;
        this.rpIp = rpIp;
        this.rpMailTitle = rpMailTitle;
        this.rpMailFrom = rpMailFrom;
        this.rpMailAddr = rpMailAddr;
        this.rpRcptDate = rpRcptDate;
        this.rpOpenDate = rpOpenDate;
        this.rpContents = rpContents;
    }

}
