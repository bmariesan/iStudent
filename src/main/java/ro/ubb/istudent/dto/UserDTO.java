package ro.ubb.istudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.istudent.domain.Gender;
import ro.ubb.istudent.domain.UserRole;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private String id;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Integer age;
    private Gender gender;
    private List<UserRole> roles;

}
