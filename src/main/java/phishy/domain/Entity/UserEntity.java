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
    private Long uid;

    public UserEntity() {}

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "user_nm")
    private String user_nm;

    @Column(name = "user_pwd")
    private String user_pwd;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "user_rank")
    private String user_rank;

    @Column(name = "corp_cd")
    private String corp_cd;

    @Column(name = "corp_nm")
    private String corp_nm;

    @Column(name = "org_cd")
    private String org_cd;

    @Column(name = "org_nm")
    private String org_nm;

    @Column(name = "level_gp")
    private String level_gp;

    @Column(name = "level_lv")
    private String level_lv;

    @Builder
    public UserEntity(Long uid, String user_id, String user_nm, String user_pwd, String user_email, String user_rank, String corp_cd, String corp_nm, String org_cd, String org_nm, String level_gp, String level_lv) {
        this.user_id    = user_id;
        this.user_nm    = user_nm;
        this.user_pwd   = user_pwd;
        this.user_email = user_email;
        this.user_rank  = user_rank;
        this.corp_cd    = corp_cd;
        this.corp_nm    = corp_nm;
        this.org_cd     = org_cd;
        this.org_nm     = org_nm;
        this.level_gp   = level_gp;
        this.level_lv   = level_lv;
    }

}

