package br.com.secret.friend.controller;

import br.com.secret.friend.dto.userDTO.UserRequesterDTO;
import br.com.secret.friend.dto.userDTO.UserResponseDTO;
import br.com.secret.friend.dto.userDTO.UserUpdateDTO;
import br.com.secret.friend.model.User;
import br.com.secret.friend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequesterDTO userDto, UriComponentsBuilder uriComponentsBuilder) {
        UserResponseDTO responseDto = userService.createUser(new User(userDto));
        URI uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(responseDto.idUser()).toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateDTO userDto){
        UserResponseDTO responseDto = userService.updateUser(userDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        Boolean user = userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        List<UserResponseDTO> listUser = userService.findAllUsers();
        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}