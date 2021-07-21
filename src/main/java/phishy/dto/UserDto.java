package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long u_id;
    private String user_id;
    private String user_nm;
    private String user_pwd;
    private String user_email;
    private String user_rank;
    private String corp_cd;
    private String corp_nm;
    private String dept_cd;
    private String dept_nm;
    private String level_gp;
    private String level_lv;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
            .u_id(u_id)
            .user_id(user_id)
            .user_nm(user_nm)
            .user_pwd(user_pwd)
            .user_email(user_email)
            .user_rank(user_rank)
            .corp_cd(corp_cd)
            .corp_nm(corp_nm)
            .dept_cd(dept_cd)
            .dept_nm(dept_nm)
            .level_gp(level_gp)
            .level_lv(level_lv)
                .build();
        return userEntity;
    }

    @Builder
    public UserDto(Long u_id, String user_id, String user_nm, String user_pwd, String user_email, String user_rank, String corp_cd, String corp_nm, String dept_cd, String dept_nm, String level_gp, String level_lv) {
        this.u_id       = u_id;
        this.user_id    = user_id;
        this.user_nm    = user_nm;
        this.user_pwd   = user_pwd;
        this.user_email = user_email;
        this.user_rank  = user_rank;
        this.corp_cd    = corp_cd;
        this.corp_nm    = corp_nm;
        this.dept_cd    = dept_cd;
        this.dept_nm    = dept_nm;
        this.level_gp   = level_gp;
        this.level_lv   = level_lv;
    }
}
