package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.UserEntity;
import phishy.domain.Repository.UserRepository;
import phishy.dto.UserDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public void registerUser(Map<String, String> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();

        userEntity.setUser_id(datas.get("user_id"));
        userEntity.setUser_pwd(passwordEncoder.encode(datas.get("user_pwd")));
        userEntity.setUser_email(datas.get("user_email"));
        userEntity.setUser_nm(datas.get("user_nm"));
        userEntity.setUser_rank(datas.get("user_rank"));
        userEntity.setCorp_cd(datas.get("corp_cd"));
        userEntity.setCorp_nm(datas.get("corp_nm"));
        userEntity.setOrg_cd(datas.get("org_cd"));
        userEntity.setOrg_nm(datas.get("org_nm"));
        userEntity.setLevel_gp(datas.get("level_gp"));
        userEntity.setLevel_lv(datas.get("level_lv"));

        userRepository.save(userEntity).getUid();
    }

    @Transactional
    public void insertMultiUsers(List<Map<String, String>> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();

        for(int idx = 0; idx < datas.size(); idx++) {
            UserEntity userEntity = userDto.toEntity();
            userEntity.setUser_id(datas.get(idx).get("user_email"));
            userEntity.setUser_pwd(passwordEncoder.encode(datas.get(idx).get("user_email")));
            userEntity.setUser_email(datas.get(idx).get("user_email"));
            userEntity.setUser_rank(datas.get(idx).get("user_rank"));
            userEntity.setUser_nm(datas.get(idx).get("user_nm"));
            userEntity.setCorp_cd(datas.get(idx).get("corp_cd"));
            userEntity.setCorp_nm(datas.get(idx).get("corp_nm"));
            userEntity.setOrg_cd(datas.get(idx).get("org_cd"));
            userEntity.setOrg_nm(datas.get(idx).get("org_nm"));
            userEntity.setLevel_gp(datas.get(idx).get("level_gp"));
            userEntity.setLevel_lv("1");

            userRepository.save(userEntity).getUid();
        }
    }

    @Transactional
    public List<UserDto> getUserlist() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            UserDto userDTO = UserDto.builder()
                    .uid(userEntity.getUid())
                    .user_nm(userEntity.getUser_nm())
                    .user_email(userEntity.getUser_email())
                    .user_rank(userEntity.getUser_rank())
                    .corp_nm(userEntity.getCorp_nm())
                    .org_nm(userEntity.getOrg_nm())
                    .build();

            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    @Transactional
    public void updateUser(Long uid, Map<String, String> datas) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(uid);
        UserEntity userEntity = userEntityWrapper.get();

        userEntity.setUser_id(datas.get("user_id"));
        userEntity.setUser_email(datas.get("user_email"));
        userEntity.setUser_nm(datas.get("user_nm"));
        userEntity.setUser_rank(datas.get("user_rank"));
        userEntity.setCorp_cd(datas.get("corp_cd"));
        userEntity.setCorp_nm(datas.get("corp_nm"));
        userEntity.setOrg_cd(datas.get("org_cd"));
        userEntity.setOrg_nm(datas.get("org_nm"));
        userEntity.setLevel_gp(datas.get("level_gp"));
        userEntity.setLevel_lv(datas.get("level_lv"));

        userRepository.save(userEntity);

    }

    @Transactional
    public UserDto getUser(Long uid) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(uid);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDTO = UserDto.builder()
                .uid(userEntity.getUid())
                .user_id(userEntity.getUser_id())
                .user_email(userEntity.getUser_email())
                .user_nm(userEntity.getUser_nm())
                .user_rank(userEntity.getUser_rank())
                .corp_cd(userEntity.getCorp_cd())
                .corp_nm(userEntity.getCorp_nm())
                .org_nm(userEntity.getOrg_nm())
                .org_cd(userEntity.getOrg_cd())
                .level_gp(userEntity.getLevel_gp())
                .level_lv(userEntity.getLevel_lv())
                .build();

        return userDTO;
    }

    @Transactional
    public void deleteUser(Long uid) {
        userRepository.deleteById(uid);
    }

}
