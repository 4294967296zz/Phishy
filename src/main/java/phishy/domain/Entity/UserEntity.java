package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;

    public UserEntity() {}

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_nm")
    private String userNm;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_rank")
    private String userRank;

    @Column(name = "corp_cd")
    private String corpCd;

    @Column(name = "corp_nm")
    private String corpNm;

    @Column(name = "dept_cd")
    private String deptCd;

    @Column(name = "dept_nm")
    private String deptNm;

    @Column(name = "level_gp")
    private String levelGp;

    @Column(name = "level_lv")
    private String levelLv;

    @Builder
    public UserEntity(Long uId, String userId, String userNm, String userPwd, String userEmail,
                      String userRank, String corpCd, String corpNm, String deptCd, String deptNm,
                      String levelGp, String levelLv) {
        this.uId       = uId;
        this.userId    = userId;
        this.userNm    = userNm;
        this.userPwd   = userPwd;
        this.userEmail = userEmail;
        this.userRank  = userRank;
        this.corpCd    = corpCd;
        this.corpNm    = corpNm;
        this.deptCd    = deptCd;
        this.deptNm    = deptNm;
        this.levelGp   = levelGp;
        this.levelLv   = levelLv;
    }

}

