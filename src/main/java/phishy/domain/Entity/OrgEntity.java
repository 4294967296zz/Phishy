package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Org")
public class OrgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long o_id;

    public OrgEntity() {}

    @Column(name = "corpCd")
    private String corpCd;

    @Column(name = "corp_nm")
    private String corpNm;

    @Column(name = "dept_cd")
    private String deptCd;

    @Column(name = "dept_nm")
    private String deptNm;

    @Column(name = "upper_cd")
    private String upperCd;

    @Builder
    public OrgEntity(Long o_id, String corpCd, String corpNm , String deptCd , String deptNm , String upperCd) {
        this.o_id = o_id;
        this.corpCd = corpCd;
        this.corpNm = corpNm;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.upperCd = upperCd;
    }

}


