package ro.ubb.istudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.User;
import ro.ubb.istudent.dto.SecurityUser;
import ro.ubb.istudent.repository.UserRepository;

import java.util.Optional;

import static ro.ubb.istudent.util.StringUtils.EMPTY_SPACE;
import static ro.ubb.istudent.util.StringUtils.NOT_FOUND;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(username);
        Optional<User> userByUserName = userRepository.findUserByUserName(username);

        if (userByEmail.isPresent()){
            User user = userByEmail.get();
            return SecurityUser.builder()
                    .id(user.getId().toHexString())
                    .roles(user.getRoles())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
        }

        if (userByUserName.isPresent()){
            User user = userByUserName.get();
            return SecurityUser.builder()
                    .id(user.getId().toHexString())
                    .roles(user.getRoles())
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .build();
        }

        throw new UsernameNotFoundException(username + EMPTY_SPACE + NOT_FOUND);
    }
}
