package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.OrgEntity;
import phishy.domain.Repository.OrgRepository;
import phishy.dto.OrgDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrgService {

    private OrgRepository orgRepository;

    @Transactional
    public void registerOrg(Map<String, String> datas) {
        OrgDto orgDto = new OrgDto();
        OrgEntity orgEntity = orgDto.toEntity();
        orgEntity.setCorp_cd(datas.get("corp_cd"));
        orgEntity.setCorp_nm(datas.get("corp_nm"));
        orgEntity.setDept_cd(datas.get("dept_cd"));
        orgEntity.setDept_nm(datas.get("dept_nm"));
        orgEntity.setUpper_cd(datas.get("upper_cd"));
        orgRepository.save(orgEntity).getO_id();
    }

    @Transactional
    public void insertMultiOrg (List<Map<String, String>> datas) {
        OrgDto orgDto = new OrgDto();

        for(int idx = 0; idx < datas.size(); idx++) {
            OrgEntity orgEntity = orgDto.toEntity();
            orgEntity.setCorp_cd(datas.get(idx).get("corp_cd"));
            orgEntity.setCorp_nm(datas.get(idx).get("corp_nm"));
            orgEntity.setDept_cd(datas.get(idx).get("dept_cd"));
            orgEntity.setDept_nm(datas.get(idx).get("dept_nm"));
            orgEntity.setUpper_cd(datas.get(idx).get("upper_cd"));

            orgRepository.save(orgEntity).getO_id();
        }
    }

    @Transactional
    public List<OrgDto> getOrglist() {
        List<OrgEntity> orgEntities = orgRepository.findAll();
        List<OrgDto> orgDtoList = new ArrayList<>();

        for(OrgEntity orgEntity : orgEntities) {
            OrgDto orgDto = OrgDto.builder()
                    .o_id(orgEntity.getO_id())
                    .corp_cd(orgEntity.getCorp_cd())
                    .corp_nm(orgEntity.getCorp_nm())
                    .dept_cd(orgEntity.getDept_cd())
                    .dept_nm(orgEntity.getDept_nm())
                    .upper_cd(orgEntity.getUpper_cd())
                    .build();

            orgDtoList.add(orgDto);
        }
        return orgDtoList;
    }

    @Transactional
    public void updateOrg(Long o_id, Map<String, String> datas) {
        Optional<OrgEntity> orgEntityWrapper = orgRepository.findById(o_id);
        OrgEntity orgEntity = orgEntityWrapper.get();

        orgEntity.setO_id(o_id);
        orgEntity.setCorp_cd(datas.get("corp_cd"));
        orgEntity.setCorp_nm(datas.get("corp_nm"));
        orgEntity.setDept_cd(datas.get("dept_cd"));
        orgEntity.setDept_nm(datas.get("dept_nm"));
        orgEntity.setUpper_cd(datas.get("upper_cd"));

        orgRepository.save(orgEntity);

    }

    @Transactional
    public OrgDto getOrg(Long o_id) {
        Optional<OrgEntity> orgEntityWrapper = orgRepository.findById(o_id);
        OrgEntity orgEntity = orgEntityWrapper.get();

        OrgDto orgDto = OrgDto.builder()
                .corp_cd(orgEntity.getCorp_cd())
                .corp_nm(orgEntity.getCorp_nm())
                .dept_cd(orgEntity.getDept_cd())
                .dept_nm(orgEntity.getDept_nm())
                .upper_cd(orgEntity.getUpper_cd())
                .build();

        return orgDto;
    }

    @Transactional
    public void deleteOrg(Long o_id) {
        orgRepository.deleteById(o_id);
    }
}
