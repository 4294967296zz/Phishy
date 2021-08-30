package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.TrainingSettingEntity;

@Getter
@Setter
@NoArgsConstructor
public class TrainingSettingDto {

    private Long trsId;
    private Long mfiId;
    private String mfi_mail_addr;
    private String mfi_mail_nm;
    private String mfi_mail_title;
    private String mfi_file_nm;
    private String mfi_nm;
    private String trsOpen;
    private String trsLink;
    private String trsAttachClick;
    private String trsAttachOpen;
    private String trsAttachNm;
    private String trsAttachType;
    private String trsAttachSize;
    private String trsPhishing;
    private String trsPhishingUrl;
    private String trsPhishingContent;

    public TrainingSettingEntity toEntity() {
        TrainingSettingEntity trainingSettingEntity = TrainingSettingEntity.builder()
                .trsId(trsId)
                .mfiId(mfiId)
                .mfi_mail_addr(mfi_mail_addr)
                .mfi_mail_nm(mfi_mail_nm)
                .mfi_mail_title(mfi_mail_title)
                .mfi_file_nm(mfi_file_nm)
                .mfi_nm(mfi_nm)
                .trsOpen(trsOpen)
                .trsLink(trsLink)
                .trsAttachClick(trsAttachClick)
                .trsAttachOpen(trsAttachOpen)
                .trsAttachNm(trsAttachNm)
                .trsAttachType(trsAttachType)
                .trsAttachSize(trsAttachSize)
                .trsPhishing(trsPhishing)
                .trsPhishingUrl(trsPhishingUrl)
                .trsPhishingContent(trsPhishingContent)
                .build();
        return trainingSettingEntity;
    }

    @Builder
    public TrainingSettingDto(Long trsId, Long mfiId, String mfi_mail_addr, String mfi_mail_nm, String mfi_nm,
                                 String mfi_mail_title, String mfi_file_nm, String trsOpen, String trsLink,
                                 String trsAttachClick, String trsAttachOpen, String trsAttachNm, String trsAttachType,
                                 String trsAttachSize, String trsPhishing, String trsPhishingUrl, String trsPhishingContent) {
        this.mfiId              = mfiId;
        this.mfi_mail_addr      = mfi_mail_addr;
        this.mfi_mail_nm        = mfi_mail_nm;
        this.mfi_mail_title     = mfi_mail_title;
        this.mfi_file_nm        = mfi_file_nm;
        this.mfi_nm             = mfi_nm;
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
