package br.com.amigo.secreto.amigo.secreto.controller;

import br.com.amigo.secreto.amigo.secreto.dto.userDTO.UserRequesterDTO;
import br.com.amigo.secreto.amigo.secreto.dto.userDTO.UserResponseDTO;
import br.com.amigo.secreto.amigo.secreto.dto.userDTO.UserUpdateDTO;
import br.com.amigo.secreto.amigo.secreto.model.User;
import br.com.amigo.secreto.amigo.secreto.service.UserService;
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
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateDTO userDto){
        UserResponseDTO responseDto = userService.updateUser(userDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        Boolean user = userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        List<UserResponseDTO> listUser = userService.findAllUsers();
        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}