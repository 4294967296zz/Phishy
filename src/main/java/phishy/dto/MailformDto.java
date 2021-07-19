package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.MailformEntity;

@Getter
@Setter
@NoArgsConstructor
public class MailformDto {

    private Long mfi_id;
    private String mfi_nm;
    private String mfi_mail_addr;
    private String mfi_mail_nm;
    private String mfi_mail_title;
    private String mfi_file_nm;

    public MailformEntity toEntity() {
        MailformEntity mailformEntity = MailformEntity.builder()
            .mfi_id(mfi_id)
            .mfi_nm(mfi_nm)
            .mfi_mail_addr(mfi_mail_addr)
            .mfi_mail_nm(mfi_mail_nm)
            .mfi_mail_title(mfi_mail_title)
            .mfi_file_nm(mfi_file_nm)
            .build();
        return mailformEntity;
    }

    @Builder
    public MailformDto(Long mfi_id, String mfi_nm, String mfi_mail_addr, String mfi_mail_nm, String mfi_mail_title, String mfi_file_nm) {
        this.mfi_id         = mfi_id;
        this.mfi_nm         = mfi_nm;
        this.mfi_mail_addr  = mfi_mail_addr;
        this.mfi_mail_nm    = mfi_mail_nm;
        this.mfi_mail_title = mfi_mail_title;
        this.mfi_file_nm    = mfi_file_nm;
    }
}
