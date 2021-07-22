package phishy.domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "training_userinfo")
public class TrainingUserinfoEntity {

    public TrainingUserinfoEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tui_id")
    private Long tuiId;

    @Column(name = "tug_id")
    private Long tugId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "dept_cd")
    private String deptCd;

    @Column(name = "dept_nm")
    private String deptNm;

    @Builder
    public TrainingUserinfoEntity(Long tuiId, Long tugId, String userId, String deptCd, String deptNm) {
        this.tuiId = tuiId;
        this.tugId = tugId;
        this.userId = userId;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
    }
}
