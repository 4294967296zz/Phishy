package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingProjectEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainingProjectDto {

    private Long trpId;
    private Long uId;
    private Long tugId;
    private Long trsId;
    private String trpNm;
    private String trpStatus;
    private String trpContent;
    private Date trpStart;
    private Date trpEnd;
    private Integer trpInterval;
    private String trpRegDate;

    public TrainingProjectEntity toEntity() {
        TrainingProjectEntity trainingProjectEntity = TrainingProjectEntity.builder()
                .trpId(trpId)
                .uId(uId)
                .tugId(tugId)
                .trsId(trsId)
                .trpNm(trpNm)
                .trpStatus(trpStatus)
                .trpContent(trpContent)
                .trpStart(trpStart)
                .trpEnd(trpEnd)
                .trpInterval(trpInterval)
                .trpRegDate(trpRegDate)
                .build();
        return trainingProjectEntity;
    }

    @Builder
    public TrainingProjectDto(Long trpId,
                                 Long uId, Long tugId, Long trsId, String trpNm,
                                 String trpStatus, String trpContent, Date trpStart, Date trpEnd,
                                 Integer trpInterval, String trpRegDate) {
        this.trpId       = trpId;
        this.uId         = uId;
        this.tugId       = tugId;
        this.trsId       = trsId;
        this.trpNm       = trpNm;
        this.trpStatus   = trpStatus;
        this.trpContent  = trpContent;
        this.trpStart    = trpStart;
        this.trpEnd      = trpEnd;
        this.trpInterval = trpInterval;
        this.trpRegDate  = trpRegDate;
    }
}
