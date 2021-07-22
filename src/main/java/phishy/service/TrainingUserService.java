package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingUsergroupEntity;
import phishy.domain.Entity.TrainingUserinfoEntity;
import phishy.domain.Repository.TrainingUsergroupRepository;
import phishy.domain.Repository.TrainingUserinfoRepository;
import phishy.dto.TrainingUsergroupDto;
import phishy.dto.TrainingUserinfoDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TrainingUserService {

    private TrainingUserinfoRepository trainingUserinfoRepository;
    private TrainingUsergroupRepository trainingUsergroupRepository;

    @Transactional
    public List<TrainingUsergroupDto> getUsergroupList() {
        List<TrainingUsergroupEntity> TUGEntities = trainingUsergroupRepository.findAll();
        List<TrainingUsergroupDto> TUGDtoList = new ArrayList<>();

        for(TrainingUsergroupEntity TrainingUsergroupEntity : TUGEntities) {
            TrainingUsergroupDto TUGDto = TrainingUsergroupDto.builder()
                    .tugId(TrainingUsergroupEntity.getTugId())
                    .tugNm(TrainingUsergroupEntity.getTugNm())
                    .build();
            TUGDtoList.add(TUGDto);
        }
        return TUGDtoList;
    }

    @Transactional
    public List<TrainingUserinfoDto> getUserinfoList() {
        List<TrainingUserinfoEntity> TUIEntities = trainingUserinfoRepository.findAll();
        List<TrainingUserinfoDto> TUIDtoList = new ArrayList<>();

        for(TrainingUserinfoEntity TrainingUserinfoEntity : TUIEntities) {
            TrainingUserinfoDto TUGDto = TrainingUserinfoDto.builder()
                    .tugId(TrainingUserinfoEntity.getTugId())
                    .userId(TrainingUserinfoEntity.getUserId())
                    .deptCd(TrainingUserinfoEntity.getDeptCd())
                    .deptNm(TrainingUserinfoEntity.getDeptNm())
                    .build();
            TUIDtoList.add(TUGDto);
        }
        return TUIDtoList;
    }

    @Transactional
    public Long registerTUG(Map<String, String> datas) {
        TrainingUsergroupDto trainingUsergroupDto = new TrainingUsergroupDto();
        TrainingUsergroupEntity trainingUsergroupEntity = trainingUsergroupDto.toEntity();
        trainingUsergroupEntity.setTugNm(datas.get("tugNm"));
        return trainingUsergroupRepository.save(trainingUsergroupEntity).getTugId();
    }

    @Transactional
    public Object registerTUI(Map<String, Object> datas, Long tugId) {
        TrainingUserinfoDto trainingUserinfoDto = new TrainingUserinfoDto();

        for(int idx = 0; idx < datas.size(); idx++) {
            TrainingUserinfoEntity trainingUserinfoEntity = trainingUserinfoDto.toEntity();
            Object user_id = datas.get(idx);
            trainingUserinfoEntity.setUserId(String.valueOf(user_id.getClass()));
            trainingUserinfoEntity.setDeptCd(String.valueOf(datas.get("dept_cd")));
            trainingUserinfoEntity.setDeptNm(String.valueOf(datas.get("dept_nm")));
            trainingUserinfoRepository.save(trainingUserinfoEntity).getTugId();
        }
        return datas.get(0);
    }
}
