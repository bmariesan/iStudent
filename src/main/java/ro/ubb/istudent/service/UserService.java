package ro.ubb.istudent.service;

import ro.ubb.istudent.dto.UserDTO;

public interface UserService {

    boolean saveUser(UserDTO user) throws Throwable;

    UserDTO findByEmail(String email);

    UserDTO findByUserName(String userName);

}
