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

    @Column(name = "corp_cd")
    private String corp_cd;

    @Column(name = "corp_nm")
    private String corp_nm;

    @Column(name = "dept_cd")
    private String dept_cd;

    @Column(name = "dept_nm")
    private String dept_nm;

    @Column(name = "upper_cd")
    private String upper_cd;

    @Builder
    public OrgEntity(Long o_id, String corp_cd, String corp_nm , String dept_cd , String dept_nm , String upper_cd) {
        this.o_id = o_id;
        this.corp_cd = corp_cd;
        this.corp_nm = corp_nm;
        this.dept_cd = dept_cd;
        this.dept_nm = dept_nm;
        this.upper_cd = upper_cd;
    }

}


