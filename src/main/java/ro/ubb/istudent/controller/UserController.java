package ro.ubb.istudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.dto.UserDTO;
import ro.ubb.istudent.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        boolean saved = false;
        try {
            saved = userService.saveUser(userDTO);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(saved);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        boolean saved = false;
        try {
            saved = userService.saveUser(userDTO);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(saved);
    }

    @RequestMapping(value = "/findByEmail/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @RequestMapping(value = "/findByUserName/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUserName(username));
    }

}
