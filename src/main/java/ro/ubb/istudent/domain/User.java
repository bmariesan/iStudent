package ro.ubb.istudent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    private String email;

    private String userName;

    private String password;

    private String address;

    private String phoneNumber;

    private Integer age;

    private Gender gender;

    private List<UserRole> roles;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(roles.get(0).name());
    }

}
