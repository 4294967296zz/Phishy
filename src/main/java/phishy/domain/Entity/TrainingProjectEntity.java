package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "training_project")
public class TrainingProjectEntity extends TimeEntity{

    public TrainingProjectEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trp_id")
    private Long trpId;

    @Column(name = "u_id")
    private Long uId;

    @Column(name = "tug_id")
    private Long tugId;

    @Column(name = "trs_id")
    private Long trsId;

    @Column(name = "trg_id")
    private Long trgId;

    @Column(name = "trp_nm")
    private String trpNm;

    @Column(name = "trp_status")
    private String trpStatus;

    @Column(name = "trp_content")
    private String trpContent;

    @Column(name = "trp_type")
    private String trpType;

    @Column(name = "trp_start")
    private Date trpStart;

    @Column(name = "trp_end")
    private Date trpEnd;

    @Column(name = "trp_interval")
    private Integer trpInterval;

    @Column(name = "trp_sent")
    private Integer trpSent;

    @Column(name = "tug_count")
    private Integer tugCount;

    @Builder
    public TrainingProjectEntity(Long trpId, Long uId, Long tugId, Long trsId, Long trgId, String trpNm, String trpType,
                                 String trpStatus, String trpContent, Date trpStart, Date trpEnd,
                                 Integer trpInterval, Integer trpSent, Integer tugCount) {
        this.trpId       = trpId;
        this.uId         = uId;
        this.tugId       = tugId;
        this.trsId       = trsId;
        this.trgId       = trgId;
        this.trpNm       = trpNm;
        this.trpType     = trpType;
        this.trpStatus   = trpStatus;
        this.trpContent  = trpContent;
        this.trpStart    = trpStart;
        this.trpEnd      = trpEnd;
        this.trpInterval = trpInterval;
        this.trpSent     = trpSent;
        this.tugCount    = tugCount;
    }

}
