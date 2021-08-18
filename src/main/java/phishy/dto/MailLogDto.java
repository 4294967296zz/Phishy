package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.MailLogEntity;


@Getter
@Setter
@NoArgsConstructor
public class MailLogDto {

    private Long mlId;
    private Long trpId;
    private Long trrId;

    public MailLogEntity toEntity() {
        MailLogEntity mailLogEntity = MailLogEntity.builder()
                .trpId(trpId)
                .trrId(trrId)
                .build();
        return mailLogEntity;
    }

    @Builder
    public MailLogDto(Long trpId, Long trrId) {

        this.trpId = trpId;
        this.trrId = trrId;
    }

}
