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
    private String corpCd;
    private String corpNm;
    private String deptCd;
    private String deptNm;
    private String upperCd;

    public OrgEntity toEntity() {
        OrgEntity orgEntity = OrgEntity.builder()
            .o_id(o_id)
            .corpCd(corpCd)
            .corpNm(corpNm)
            .deptCd(deptCd)
            .deptNm(deptNm)
            .upperCd(upperCd)
                .build();
        return orgEntity;
    }

    @Builder
    public OrgDto(Long o_id, String corpCd, String corpNm , String deptCd , String deptNm , String upperCd) {
        this.o_id = o_id;
        this.corpCd = corpCd;
        this.corpNm = corpNm;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.upperCd = upperCd;
    }

}
