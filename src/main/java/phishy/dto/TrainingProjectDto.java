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
    private Long trgId;
    private String trpNm;
    private String trpType;
    private String trpStatus;
    private String trpContent;
    private Date trpStart;
    private Date trpEnd;
    private Integer trpInterval;
    private Integer trpSent;

    public TrainingProjectEntity toEntity() {
        TrainingProjectEntity trainingProjectEntity = TrainingProjectEntity.builder()
                .trpId(trpId)
                .uId(uId)
                .tugId(tugId)
                .trsId(trsId)
                .trgId(trgId)
                .trpNm(trpNm)
                .trpType(trpType)
                .trpStatus(trpStatus)
                .trpContent(trpContent)
                .trpStart(trpStart)
                .trpEnd(trpEnd)
                .trpInterval(trpInterval)
                .trpSent(trpSent)
                .build();
        return trainingProjectEntity;
    }

    @Builder
    public TrainingProjectDto(Long trpId, Long uId, Long tugId, Long trsId, Long trgId, String trpNm, String trpType,
                                 String trpStatus, String trpContent, Date trpStart, Date trpEnd,
                                 Integer trpInterval, Integer trpSent) {
        this.trpId       = trpId;
        this.uId         = uId;
        this.tugId       = tugId;
        this.trsId       = trsId;
        this.trgId       = trgId;
        this.trpNm       = trpNm;
        this.trpType     = trpType;
        this.trpStatus   = trpStatus;
        this.trpContent  = trpContent;
        this.trpStart    = trpStart;
        this.trpEnd      = trpEnd;
        this.trpInterval = trpInterval;
        this.trpSent     = trpSent;
    }
}
