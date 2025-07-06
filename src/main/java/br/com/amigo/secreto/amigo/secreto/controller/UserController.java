package br.com.amigo.secreto.amigo.secreto.controller;

import br.com.amigo.secreto.amigo.secreto.dto.UserDTO;
import br.com.amigo.secreto.amigo.secreto.dto.UserRequesterDTO;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequesterDTO userDto) {
        User newUser = User.builder()
                .email(userDto.email())
                .hashedPassword(userDto.password())
                .authProvider(userDto.authProvider())
                .name(userDto.name())
                .idGoogle(userDto.idGoogle())
                .phoneNumber(userDto.phoneNumber())
                .build();

        try {
            User userSave = userService.createUser(newUser);
            UserResponseDTO responseDto = new UserResponseDTO(
                    userSave.getIdUser(),
                    userSave.getName(),
                    userSave.getEmail(),
                    userSave.getAvatarUrl()
            );
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