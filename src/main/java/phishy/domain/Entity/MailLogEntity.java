package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mail_log")
public class MailLogEntity extends TimeEntity{
    public MailLogEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ml_id")
    private Long mlId;

    @Column(name = "trp_id")
    private Long trpId;

    @Column(name = "trr_id")
    private Long trrId;

    @Column(name = "ml_addr")
    private String mlAddr;


    @Builder
    public MailLogEntity(Long mlId, Long trpId, Long trrId, String mlAddr) {
        this.mlId = mlId;
        this.trpId = trpId;
        this.trrId = trrId;
        this.mlAddr = mlAddr;
    }
}
