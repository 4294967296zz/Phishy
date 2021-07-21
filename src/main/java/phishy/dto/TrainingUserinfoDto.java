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
    private String userId;
    private String deptCd;
    private String deptNm;

    public TrainingUserinfoEntity toEntity() {
        TrainingUserinfoEntity trainingUserinfoEntity = TrainingUserinfoEntity.builder()
                .tugId(tugId)
                .userId(userId)
                .deptCd(deptCd)
                .deptNm(deptNm)
                .build();
        return trainingUserinfoEntity;
    }

    @Builder
    public TrainingUserinfoDto(Long tugId, String userId, String deptCd, String deptNm) {
        this.tugId = tugId;
        this.userId = userId;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
    }

}
