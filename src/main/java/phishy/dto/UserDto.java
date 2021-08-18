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

    private Long uId;
    private String userId;
    private String userNm;
    private String userPwd;
    private String userEmail;
    private String userRank;
    private String corpCd;
    private String corpNm;
    private String deptCd;
    private String deptNm;
    private String levelGp;
    private String levelLv;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .uId(uId)
                .userId(userId)
                .userNm(userNm)
                .userPwd(userPwd)
                .userEmail(userEmail)
                .userRank(userRank)
                .corpCd(corpCd)
                .corpNm(corpNm)
                .deptCd(deptCd)
                .deptNm(deptNm)
                .levelGp(levelGp)
                .levelLv(levelLv)
                .build();
        return userEntity;
    }

    @Builder
    public UserDto(Long uId, String userId, String userNm, String userPwd, String userEmail,
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
