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
        try {
            UserResponseDTO responseDto = userService.createUser(new User(userDto));
            URI uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(responseDto.idUser()).toUri();

            return ResponseEntity.created(uri).body(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateDTO userDto){
        try {
            UserResponseDTO checkIdUser = userService.findById(userDto.userId());
            if (checkIdUser == null){
                return ResponseEntity.notFound().build();
            }

            UserResponseDTO responseDto = userService.updateUser(userDto);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            Boolean user = userService.deleteById(id);
            if(user){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        try {
            List<UserResponseDTO> listUser = userService.findAllUsers();
            return ResponseEntity.ok(listUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        try {
            UserResponseDTO user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}