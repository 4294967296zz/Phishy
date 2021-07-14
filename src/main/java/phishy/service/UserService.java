package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.UserEntity;
import phishy.domain.Repository.UserRepository;
import phishy.dto.UserDto;

import javax.transaction.Transactional;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public void registerUser(Map<String, String> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();
        String status = datas.get("dept");
        userEntity.setUser_id(datas.get("email"));
        userEntity.setUser_pwd(passwordEncoder.encode(datas.get("pwd")));
        userEntity.setUser_email(datas.get("email"));
        userEntity.setUser_rank(datas.get("ranks"));
        userEntity.setUser_nm(datas.get("name"));
        userEntity.setCorp_cd(datas.get("corp_cd"));
        userEntity.setCorp_nm(datas.get("corp_nm"));
        userEntity.setOrg_cd(datas.get("org_cd"));
        userEntity.setOrg_nm(datas.get("org_nm"));
        userEntity.setLevel_gp(datas.get("level_gp"));
        userEntity.setLevel_lv(datas.get("level_lv"));
        userRepository.save(userEntity).getUid();
    }
}
