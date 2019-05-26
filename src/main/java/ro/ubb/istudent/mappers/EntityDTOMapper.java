package ro.ubb.istudent.mappers;

import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.User;
import ro.ubb.istudent.dto.UserDTO;

@Component
public class EntityDTOMapper {

    public UserDTO toUserDTO(User user){
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId().toHexString())
                .email(user.getEmail())
                .userName(user.getUserName())
                .roles(user.getRoles())
                .address(user.getAddress())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .build();
    }

}
