package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingUsergroupEntity;

@Getter
@Setter
@NoArgsConstructor
public class TrainingUsergroupDto {
    private Long tugId;
    private String tugNm;
    private Integer tugCount;

    public TrainingUsergroupEntity toEntity() {
        TrainingUsergroupEntity trainingUsergroupEntity = TrainingUsergroupEntity.builder()
                .tugId(tugId)
                .tugNm(tugNm)
                .tugCount(tugCount)
                .build();
        return trainingUsergroupEntity;
    }

    @Builder
    public TrainingUsergroupDto(Long tugId, String tugNm, Integer tugCount) {
        this.tugId      = tugId;
        this.tugNm      = tugNm;
        this.tugCount   = tugCount;
    }
}
