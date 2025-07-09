package br.com.secret.friend.service;

import br.com.secret.friend.dto.userDTO.UserResponseDTO;
import br.com.secret.friend.dto.userDTO.UserUpdateDTO;
import br.com.secret.friend.model.User;
import br.com.secret.friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserResponseDTO createUser(User user) {
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        User userSaved = userRepository.save(user);
        return new UserResponseDTO(userSaved);
    }

    public List<UserResponseDTO> findAllUsers(){
        return converterToDTO(userRepository.findAll());
    }

    public UserResponseDTO findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User u = user.get();
            return new UserResponseDTO(u);
        } else {
            return null;
        }
    }

    public List<UserResponseDTO> converterToDTO(List<User> users){
        return users.stream()
                    .map(u -> new UserResponseDTO(u.getIdUser(),u.getName(), u.getAvatarUrl()))
                    .collect(Collectors.toList());
    }

    @Transactional
    public Boolean deleteById(Long id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public UserResponseDTO updateUser(UserUpdateDTO userDto) {
        User userUpdate = userRepository.getReferenceById(userDto.userId());
        userUpdate.updateInformation(userDto);

        if(userDto.password() != null){
            userUpdate.setHashedPassword(passwordEncoder.encode(userDto.password()));
        }

        return new UserResponseDTO(userUpdate);
    }
}