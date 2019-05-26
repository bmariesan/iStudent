package ro.ubb.istudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.User;
import ro.ubb.istudent.dto.UserDTO;
import ro.ubb.istudent.mappers.DTOToEntityMapper;
import ro.ubb.istudent.mappers.EntityDTOMapper;
import ro.ubb.istudent.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DTOToEntityMapper dtoToEntityMapper;
    private final EntityDTOMapper entityDTOMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DTOToEntityMapper dtoToEntityMapper,
                           EntityDTOMapper entityDTOMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.dtoToEntityMapper = dtoToEntityMapper;
        this.entityDTOMapper = entityDTOMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Throwable {
        User user = dtoToEntityMapper.toUser(userDTO);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user) != null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Wrong email"));
        return entityDTOMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findByUserName(String userName) {
        Optional<User> userOptional = userRepository.findUserByUserName(userName);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Wrong userName"));
        return entityDTOMapper.toUserDTO(user);
    }

}



