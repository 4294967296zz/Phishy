package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.UserEntity;
import phishy.domain.Repository.UserRepository;
import phishy.dto.UserDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private EntityManager em;
    private UserRepository userRepository;

    @Transactional
    public void registerUser(Map<String, String> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();

        userEntity.setUserId(datas.get("user_id"));
        userEntity.setUserPwd(passwordEncoder.encode(datas.get("user_pwd")));
        userEntity.setUserEmail(datas.get("user_email"));
        userEntity.setUserNm(datas.get("user_nm"));
        userEntity.setUserRank(datas.get("user_rank"));
        userEntity.setCorpCd(datas.get("corp_cd"));
        userEntity.setCorpNm(datas.get("corp_nm"));
        userEntity.setDeptCd(datas.get("dept_cd"));
        userEntity.setDeptNm(datas.get("dept_nm"));
        userEntity.setLevelGp(datas.get("level_gp"));
        userEntity.setLevelLv(datas.get("level_lv"));

        userRepository.save(userEntity).getUId();
    }

    @Transactional
    public void insertMultiUsers(List<Map<String, String>> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();

        for(int idx = 0; idx < datas.size(); idx++) {
            UserEntity userEntity = userDto.toEntity();
            userEntity.setUserId(datas.get(idx).get("user_email"));
            userEntity.setUserPwd(passwordEncoder.encode(datas.get(idx).get("user_email")));
            userEntity.setUserEmail(datas.get(idx).get("user_email"));
            userEntity.setUserNm(datas.get(idx).get("user_nm"));
            userEntity.setUserRank(datas.get(idx).get("user_rank"));
            userEntity.setCorpCd(datas.get(idx).get("corp_cd"));
            userEntity.setCorpNm(datas.get(idx).get("corp_nm"));
            userEntity.setDeptCd(datas.get(idx).get("dept_cd"));
            userEntity.setDeptNm(datas.get(idx).get("dept_nm"));
            userEntity.setLevelGp(datas.get(idx).get("level_gp"));
            userEntity.setLevelLv("1");

            userRepository.save(userEntity).getUId();
        }
    }
     @Transactional
    public List<UserDto> getUserlistsforTUI(List<Long> u_ids) {
        List<UserEntity> userEntities = userRepository.findAllById(u_ids);
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            UserDto userDTO = UserDto.builder()
                    .uId(userEntity.getUId())
                    .deptCd(userEntity.getDeptCd())
                    .deptNm(userEntity.getDeptNm())
                    .userEmail(userEntity.getUserEmail())
                    .build();
            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    @Transactional
    public List<UserDto> getUserlist() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            UserDto userDTO = UserDto.builder()
                    .uId(userEntity.getUId())
                    .userNm(userEntity.getUserNm())
                    .userEmail(userEntity.getUserEmail())
                    .userRank(userEntity.getUserRank())
                    .corpNm(userEntity.getCorpNm())
                    .deptNm(userEntity.getDeptNm())
                    .build();

            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    @Transactional
    public void updateUser(Long u_id, Map<String, String> datas) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(u_id);
        UserEntity userEntity = userEntityWrapper.get();

        userEntity.setUserId(datas.get("user_id"));
        userEntity.setUserEmail(datas.get("user_email"));
        userEntity.setUserNm(datas.get("user_nm"));
        userEntity.setUserRank(datas.get("user_rank"));
        userEntity.setCorpCd(datas.get("corp_cd"));
        userEntity.setCorpNm(datas.get("corp_nm"));
        userEntity.setDeptCd(datas.get("dept_cd"));
        userEntity.setDeptNm(datas.get("dept_nm"));
        userEntity.setLevelGp(datas.get("level_gp"));
        userEntity.setLevelLv(datas.get("level_lv"));

        userRepository.save(userEntity);

    }

    @Transactional
    public UserDto getUser(Long u_id) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(u_id);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDTO = UserDto.builder()
                .uId(userEntity.getUId())
                .userId(userEntity.getUserId())
                .userEmail(userEntity.getUserEmail())
                .userNm(userEntity.getUserNm())
                .userRank(userEntity.getUserRank())
                .corpCd(userEntity.getCorpCd())
                .corpNm(userEntity.getCorpNm())
                .deptNm(userEntity.getDeptNm())
                .deptCd(userEntity.getDeptCd())
                .levelGp(userEntity.getLevelGp())
                .levelLv(userEntity.getLevelLv())
                .build();

        return userDTO;
    }

    @Transactional
    public UserDto getUserByEmail(String userEmail) {
        Optional<UserEntity> userEntityWrapper = userRepository.findAllByUserEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDTO = UserDto.builder()
                .uId(userEntity.getUId())
                .userEmail(userEntity.getUserEmail())
                .userNm(userEntity.getUserNm())
                .userRank(userEntity.getUserRank())
                .build();

        return userDTO;
    }

    @Transactional
    public void deleteUser(Long u_id) {
        userRepository.deleteById(u_id);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteUsers(List<Long> u_id) {
        List<UserEntity> userEntities = userRepository.findAllById(u_id);
        for(UserEntity userEntity : userEntities) {
            userRepository.deleteById(userEntity.getUId());
        }
    }


}
