package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "training_setting")
public class TrainingSettingEntity {

    public TrainingSettingEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trs_id")
    private Long trsId;

    @Column(name = "mfi_id")
    private Long mfiId;

    // 훈련메일 발신자 주소
    @Column(name = "mfi_mail_addr")
    private String mfi_mail_addr;

    // 훈련메일 발신자 이름
    @Column(name = "mfi_mail_nm")
    private String mfi_mail_nm;

    // 훈련 메일 제목
    @Column(name = "mfi_mail_title")
    private String mfi_mail_title;

    // 훈련양식 파일 이름
    @Column(name = "mfi_file_nm")
    private String mfi_file_nm;

    // 메일 열람 여부
    @Column(name = "trs_open")
    private String trsOpen;

    // 링크(버튼)클릭여부
    @Column(name = "trs_link")
    private String trsLink;

    // 첨부파일 클릭여부
    @Column(name = "trs_attach_click")
    private String trsAttachClick;

    // 첨부파일 실행여부
    @Column(name = "trs_attach_open")
    private String trsAttachOpen;

    // 첨부파일 이름
    @Column(name = "trs_attach_nm")
    private String trsAttachNm;

    // 첨부파일 형식(파일)
    @Column(name = "trs_attach_type")
    private String trsAttachType;

    //첨부파일 사이즈
    @Column(name = "trs_attach_size")
    private String trsAttachSize;

    // 피싱사이트 활성 여부
    @Column(name = "trs_phishing")
    private String trsPhishing;

    // 피싱사이트 URL
    @Column(name = "trs_phishing_url")
    private String trsPhishingUrl;

    // 피싱사이트입력여부
    @Column(name = "trs_phishing_content")
    private String trsPhishingContent;

    @Builder
    public TrainingSettingEntity(Long trsId, Long mfiId, String mfi_mail_addr, String mfi_mail_nm,
                                 String mfi_mail_title, String mfi_file_nm, String trsOpen, String trsLink,
                                 String trsAttachClick, String trsAttachOpen, String trsAttachNm, String trsAttachType,
                                 String trsAttachSize, String trsPhishing, String trsPhishingUrl, String trsPhishingContent) {
        this.mfiId              = mfiId;
        this.mfi_mail_addr      = mfi_mail_addr;
        this.mfi_mail_nm        = mfi_mail_nm;
        this.mfi_mail_title     = mfi_mail_title;
        this.mfi_file_nm        = mfi_file_nm;
        this.trsOpen            = trsOpen;
        this.trsLink            = trsLink;
        this.trsAttachClick     = trsAttachClick;
        this.trsAttachOpen      = trsAttachOpen;
        this.trsAttachNm        = trsAttachNm;
        this.trsAttachType      = trsAttachType;
        this.trsAttachSize      = trsAttachSize;
        this.trsPhishing        = trsPhishing;
        this.trsPhishingUrl     = trsPhishingUrl;
        this.trsPhishingContent = trsPhishingContent;
    }

}
