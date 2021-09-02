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
    private String mlAddr;

    public MailLogEntity toEntity() {
        MailLogEntity mailLogEntity = MailLogEntity.builder()
                .mlId(mlId)
                .trpId(trpId)
                .trrId(trrId)
                .mlAddr(mlAddr)
                .build();
        return mailLogEntity;
    }

    @Builder
    public MailLogDto(Long mlId, Long trpId, Long trrId, String mlAddr) {
        this.mlId = mlId;
        this.trpId = trpId;
        this.trrId = trrId;
        this.mlAddr = mlAddr;
    }

}
