package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "mail_form")
public class MailformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mfi_id;

    public MailformEntity() {}

    @Column(name = "mfi_nm")
    private String mfi_nm;

    @Column(name = "mfi_mail_addr")
    private String mfi_mail_addr;

    @Column(name = "mfi_mail_nm")
    private String mfi_mail_nm;

    @Column(name = "mfi_mail_title")
    private String mfi_mail_title;

    @Column(name = "mfi_file_nm")
    private String mfi_file_nm;

    @Builder
    public MailformEntity(Long mfi_id, String mfi_nm, String mfi_mail_addr, String mfi_mail_nm, String mfi_mail_title, String mfi_file_nm) {
        this.mfi_id         = mfi_id;
        this.mfi_nm         = mfi_nm;
        this.mfi_mail_addr  = mfi_mail_addr;
        this.mfi_mail_nm    = mfi_mail_nm;
        this.mfi_mail_title = mfi_mail_title;
        this.mfi_file_nm    = mfi_file_nm;
    }

}
