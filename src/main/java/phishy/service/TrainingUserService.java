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
}
