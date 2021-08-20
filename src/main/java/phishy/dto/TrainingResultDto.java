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
    private Integer trpSent;
    private String userId;
    private String userRank;
    private String userNm;
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
                .trpSent(trpSent)
                .userId(userId)
                .userRank(userRank)
                .userNm(userNm)
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
    public TrainingResultDto(Long trrId, Long trpId, Long trsId, Long tugId, Integer trpSent, String userId, String userRank, String userNm,
                                String trrOpen, LocalDateTime trrOpenDate, String trrLink, LocalDateTime trrLinkDate,
                                String trrAttachClick, LocalDateTime trrAttachClickDate, String trrAttachOpen,
                                LocalDateTime trrAttachOpenDate, String trrPhishingclick, LocalDateTime trrPhishingclickDate,
                                String trrPhishingContent, String trrReturnIp, String trrReturnUserinfo) {

        this.trrId                  = trrId;
        this.trpId                  = trpId;
        this.trsId                  = trsId;
        this.tugId                  = tugId;
        this.trpSent                = trpSent;
        this.userId                 = userId;
        this.userRank               = userRank;
        this.userNm                 = userNm;
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
