package phishy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phishy.domain.Entity.OrgEntity;

@Getter
@Setter
@NoArgsConstructor
public class OrgDto {

    private Long o_id;
    private String corp_cd;
    private String corp_nm;
    private String dept_cd;
    private String dept_nm;
    private String upper_cd;

    public OrgEntity toEntity() {
        OrgEntity orgEntity = OrgEntity.builder()
            .o_id(o_id)
            .corp_cd(corp_cd)
            .corp_nm(corp_nm)
            .dept_cd(dept_cd)
            .dept_nm(dept_nm)
            .upper_cd(upper_cd)
                .build();
        return orgEntity;
    }

    @Builder
    public OrgDto(Long o_id, String corp_cd, String corp_nm , String dept_cd , String dept_nm , String upper_cd) {
        this.o_id = o_id;
        this.corp_cd = corp_cd;
        this.corp_nm = corp_nm;
        this.dept_cd = dept_cd;
        this.dept_nm = dept_nm;
        this.upper_cd = upper_cd;
    }

}
