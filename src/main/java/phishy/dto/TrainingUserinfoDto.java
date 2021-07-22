package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingUserinfoEntity;

@Getter
@Setter
@NoArgsConstructor
public class TrainingUserinfoDto {

    private Long tugId;
    private Long tuiId;
    private String userId;
    private String deptCd;
    private String deptNm;

    public TrainingUserinfoEntity toEntity() {
        TrainingUserinfoEntity trainingUserinfoEntity = TrainingUserinfoEntity.builder()
                .tuiId(tuiId)
                .tugId(tugId)
                .userId(userId)
                .deptCd(deptCd)
                .deptNm(deptNm)
                .build();
        return trainingUserinfoEntity;
    }

    @Builder
    public TrainingUserinfoDto(Long tuiId, Long tugId, String userId, String deptCd, String deptNm) {
        this.tuiId = tuiId;
        this.tugId = tugId;
        this.userId = userId;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
    }

}
