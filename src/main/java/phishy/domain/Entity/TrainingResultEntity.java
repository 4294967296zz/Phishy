package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "training_result")
public class TrainingResultEntity extends TimeEntity{

    public void TrainingResultEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trr_id")
    private Long trrId;

    @Column(name = "trp_id")
    private Long trpId;

    @Column(name = "trs_id")
    private Long trsId;

    @Column(name = "tug_id")
    private Long tugId;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "user_rank")
    private String user_rank;

    // 메일 열람 여부
    @Column(name = "trr_open")
    private String trrOpen;

    // 메일 열람 시간
    @Column(name = "trr_open_date")
    private LocalDateTime trrOpenDate;

    // 링크(버튼)클릭여부
    @Column(name = "trr_link")
    private String trrLink;

    // 링크(버튼)클릭 시간
    @Column(name = "trr_link_date")
    private LocalDateTime trrLinkDate;

    // 첨부파일 다운 여부
    @Column(name = "trr_attach_click")
    private String trrAttachClick;

    // 첨부파일 다운 시간
    @Column(name = "trr_attach_click_date")
    private LocalDateTime trrAttachClickDate;

    // 첨부파일 실행여부
    @Column(name = "trr_attach_open")
    private String trrAttachOpen;

    // 첨부파일 실행 시간
    @Column(name = "trr_attach_open_date")
    private LocalDateTime trrAttachOpenDate;

    // 피싱사이트 활성 여부
    @Column(name = "trr_phishing_click")
    private String trrPhishingclick;

    // 피싱사이트 활성 여부
    @Column(name = "trr_phishing_click_date")
    private LocalDateTime trrPhishingclickDate;

    // 피싱사이트 입력 정보
    @Column(name = "trr_phishing_content")
    private String trrPhishingContent;

    // 대상자 IP주소
    @Column(name = "trr_return_ip")
    private String trrReturnIp;

    // 대상자 접속정보
    @Column(name = "trr_return_userinfo")
    private String trrReturnUserinfo;


    @Builder
    public TrainingResultEntity(Long trrId, Long trpId, Long trsId, Long tugId, String user_id, String user_rank,
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


