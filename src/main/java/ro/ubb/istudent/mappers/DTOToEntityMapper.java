package ro.ubb.istudent.mappers;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.User;
import ro.ubb.istudent.dto.UserDTO;

@Component
public class DTOToEntityMapper {

    // perform mapping logic
    public User toUser(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .userName(userDTO.getUserName())
                .roles(userDTO.getRoles())
                .address(userDTO.getAddress())
                .age(userDTO.getAge())
                .phoneNumber(userDTO.getPhoneNumber())
                .gender(userDTO.getGender())
                .build();
        if(userDTO.getId() != null){
            user.setId(new ObjectId(userDTO.getId()));
        }
        return user;
    }

}
