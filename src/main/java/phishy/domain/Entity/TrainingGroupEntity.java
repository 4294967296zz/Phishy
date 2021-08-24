package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "training_group")
public class TrainingGroupEntity extends TimeEntity{

    public TrainingGroupEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trg_id")
    private Long trgId;

    @Column(name = "trg_nm")
    private String trgNm;

    @Column(name = "trp_desc")
    private String trgDesc;

    @Column(name = "trg_start")
    private Date trgStart;

    @Column(name = "trg_end")
    private Date trgEnd;

    @Column(name = "trg_status")
    private String trgStatus;

    @Column(name = "trg_count")
    private Integer trgCount;

    @Builder
    public TrainingGroupEntity(Long trgId,String trgNm,String trgDesc,Date trgStart,
                               Date trgEnd,String trgStatus, Integer trgCount) {
        this.trgId     = trgId;
        this.trgNm     = trgNm;
        this.trgDesc   = trgDesc;
        this.trgStart  = trgStart;
        this.trgEnd    = trgEnd;
        this.trgStatus = trgStatus;
        this.trgCount = trgCount;
    }

}
