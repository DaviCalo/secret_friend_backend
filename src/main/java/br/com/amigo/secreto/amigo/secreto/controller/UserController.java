package br.com.amigo.secreto.amigo.secreto.controller;

import br.com.amigo.secreto.amigo.secreto.dto.UserDTO;
import br.com.amigo.secreto.amigo.secreto.dto.UserRegisterDTO;
import br.com.amigo.secreto.amigo.secreto.dto.UserResponseDTO;
import br.com.amigo.secreto.amigo.secreto.model.User;
import br.com.amigo.secreto.amigo.secreto.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegisterDTO userDto) {
        User newUser = new User.Builder()
                .email(userDto.getEmail())
                .hashedPassword(userDto.getPassword())
                .authProvider(userDto.getAuthProvider())
                .name(userDto.getName())
                .avatarUrl(userDto.getAvatarUrl())
                .idGoogle(userDto.getIdGoogle())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        try {
            User userSave = userService.createUser(newUser);
            UserResponseDTO responseDto = new UserResponseDTO(userSave);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<UserDTO> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }
}