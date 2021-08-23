package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingGroupEntity;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainingGroupDto {

    private Long trgId;
    private String trgNm;
    private String trgDesc;
    private Date trgStart;
    private Date trgEnd;
    private String trgStatus;

    public TrainingGroupEntity toEntity() {
        TrainingGroupEntity trainingGroupEntity = TrainingGroupEntity.builder()
                .trgId(trgId)
                .trgNm(trgNm)
                .trgDesc(trgDesc)
                .trgStart(trgStart)
                .trgEnd(trgEnd)
                .trgStatus(trgStatus)
                .build();
        return trainingGroupEntity;
    }

    @Builder
    public TrainingGroupDto(Long trgId,String trgNm,String trgDesc,Date trgStart,
                               Date trgEnd,String trgStatus) {
        this.trgId     = trgId;
        this.trgNm     = trgNm;
        this.trgDesc   = trgDesc;
        this.trgStart  = trgStart;
        this.trgEnd    = trgEnd;
        this.trgStatus = trgStatus;
    }
}
