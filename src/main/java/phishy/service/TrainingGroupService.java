package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingGroupEntity;
import phishy.domain.Repository.TrainingGroupRepository;
import phishy.dto.TrainingGroupDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TrainingGroupService {

    private TrainingGroupRepository trainingGroupRepository;

    @Transactional
    public void registerTRG(Map<String, String> datas, Date trgStart, Date trgEnd) {

        TrainingGroupDto TRGDto = new TrainingGroupDto();
        TrainingGroupEntity TRGEntity = TRGDto.toEntity();

        TRGEntity.setTrgNm(datas.get("trgNm"));
        TRGEntity.setTrgDesc(datas.get("trgDesc"));
        TRGEntity.setTrgStatus("진행중");
        TRGEntity.setTrgStart(trgStart);
        TRGEntity.setTrgEnd(trgEnd);

        trainingGroupRepository.save(TRGEntity).getTrgId();
    }

    @Transactional
    public List<TrainingGroupDto> getTRGs() {
        List<TrainingGroupEntity> trgEntities = trainingGroupRepository.findAll(Sort.by(Sort.Direction.DESC, "trgId"));
        List<TrainingGroupDto> trgDtoList = new ArrayList<>();

        for(TrainingGroupEntity trgEntity : trgEntities) {
            TrainingGroupDto trpDto = TrainingGroupDto.builder()
                    .trgId(trgEntity.getTrgId())
                    .trgNm(trgEntity.getTrgNm())
                    .trgDesc(trgEntity.getTrgDesc())
                    .trgStart(trgEntity.getTrgStart())
                    .trgEnd(trgEntity.getTrgEnd())
                    .trgStatus(trgEntity.getTrgStatus())
                    .build();
            trgDtoList.add(trpDto);
        }
        return trgDtoList;
    }
}
