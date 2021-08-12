package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingResultEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TrainingResultDto {

    private Long trrId;
    private Long trpId;
    private Long trsId;
    private Long tugId;
    private String user_id;
    private String user_rank;
    private String trrOpen;
    private LocalDateTime trrOpenDate;
    private String trrLink;
    private LocalDateTime trrLinkDate;
    private String trrAttachClick;
    private LocalDateTime trrAttachClickDate;
    private String trrAttachOpen;
    private LocalDateTime trrAttachOpenDate;
    private String trrPhishingclick;
    private LocalDateTime trrPhishingclickDate;
    private String trrPhishingContent;
    private String trrReturnIp;
    private String trrReturnUserinfo;

    public TrainingResultEntity toEntity() {
        TrainingResultEntity trainingResultEntity = TrainingResultEntity.builder()
                .trrId(trrId)
                .trpId(trpId)
                .trsId(trsId)
                .tugId(tugId)
                .user_id(user_id)
                .user_rank(user_rank)
                .trrOpen(trrOpen)
                .trrOpenDate(trrOpenDate)
                .trrLink(trrLink)
                .trrLinkDate(trrLinkDate)
                .trrAttachClick(trrAttachClick)
                .trrAttachClickDate(trrAttachClickDate)
                .trrAttachOpen(trrAttachOpen)
                .trrAttachOpenDate(trrAttachOpenDate)
                .trrPhishingclick(trrPhishingclick)
                .trrPhishingclickDate(trrPhishingclickDate)
                .trrPhishingContent(trrPhishingContent)
                .trrReturnIp(trrReturnIp)
                .trrReturnUserinfo(trrReturnUserinfo)
                .build();
        return trainingResultEntity;
    }

    @Builder
    public TrainingResultDto(Long trrId, Long trpId, Long trsId, Long tugId, String user_id, String user_rank,
                                String trrOpen, LocalDateTime trrOpenDate, String trrLink, LocalDateTime trrLinkDate,
                                String trrAttachClick, LocalDateTime trrAttachClickDate, String trrAttachOpen,
                                LocalDateTime trrAttachOpenDate, String trrPhishingclick, LocalDateTime trrPhishingclickDate,
                                String trrPhishingContent, String trrReturnIp, String trrReturnUserinfo) {

        this.trrId                  = trrId;
        this.trpId                  = trpId;
        this.trsId                  = trsId;
        this.tugId                  = tugId;
        this.user_id                = user_id;
        this.user_rank              = user_rank;
        this.trrOpen                = trrOpen;
        this.trrOpenDate            = trrOpenDate;
        this.trrLink                = trrLink;
        this.trrLinkDate            = trrLinkDate;
        this.trrAttachClick         = trrAttachClick;
        this.trrAttachClickDate     = trrAttachClickDate;
        this.trrAttachOpen          = trrAttachOpen;
        this.trrAttachOpenDate      = trrAttachOpenDate;
        this.trrPhishingclick       = trrPhishingclick;
        this.trrPhishingclickDate   = trrPhishingclickDate;
        this.trrPhishingContent     = trrPhishingContent;
        this.trrReturnIp            = trrReturnIp;
        this.trrReturnUserinfo      = trrReturnUserinfo;
    }


}
