package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report")
public class ReportEntity extends TimeEntity{

    public ReportEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rp_id")
    private Long rpId;

    @Column(name = "trr_id")
    private Long trrId;

    @Column(name = "rp_username")
    private String rpUsername;

    @Column(name = "rp_deptnm")
    private String rpDeptnm;

    @Column(name = "rp_ip")
    private String rpIp;

    @Column(name = "rp_mail_title")
    private String rpMailTitle;

    @Column(name = "rp_mail_from")
    private String rpMailFrom;

    @Column(name = "rp_mail_addr")
    private String rpMailAddr;

    @Column(name = "rp_rcpt_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime rpRcptDate;

    @Column(name = "rp_open_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime rpOpenDate;

    @Column(name = "rp_contents")
    private String rpContents;

    @Builder
    public ReportEntity(Long rpId,Long trrId,String rpUsername,String rpDeptnm,String rpIp,
                        String rpMailTitle,String rpMailFrom,String rpMailAddr,LocalDateTime rpRcptDate,
                        LocalDateTime rpOpenDate,String rpContents) {
        this.rpId = rpId;
        this.trrId = trrId;
        this.rpUsername = rpUsername;
        this.rpDeptnm = rpDeptnm;
        this.rpIp = rpIp;
        this.rpMailTitle = rpMailTitle;
        this.rpMailFrom = rpMailFrom;
        this.rpMailAddr = rpMailAddr;
        this.rpRcptDate = rpRcptDate;
        this.rpOpenDate = rpOpenDate;
        this.rpContents = rpContents;
    }

}
