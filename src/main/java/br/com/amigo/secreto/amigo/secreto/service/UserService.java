package br.com.amigo.secreto.amigo.secreto.service;

import br.com.amigo.secreto.amigo.secreto.dto.UserDTO;
import br.com.amigo.secreto.amigo.secreto.model.User;
import br.com.amigo.secreto.amigo.secreto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getHashedPassword());
        user.setHashedPassword(hashedPassword);
        return userRepository.save(user);
    }

    public List<UserDTO> findAllUsers(){
        return converterToDTO(userRepository.findAll());
    }

    public UserDTO findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User u = user.get();
            return new UserDTO(u.getEmail(), u.getName(), u.getAvatarUrl(), u.getIdGoogle(), u.getPhoneNumber(), u.getCreatedAt(), u.getUpdatedAt());
        } else {
            return null;
        }
    }

    public List<UserDTO> converterToDTO(List<User> users){
        return users.stream()
                    .map(u -> new UserDTO(u.getEmail(), u.getName(), u.getAvatarUrl(), u.getIdGoogle(), u.getPhoneNumber(), u.getCreatedAt(), u.getUpdatedAt()))
                    .collect(Collectors.toList());
    }
}