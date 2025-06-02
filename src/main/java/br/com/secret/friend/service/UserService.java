package br.com.secret.friend.service;

import br.com.secret.friend.dto.userDTO.UserRequesterDTO;
import br.com.secret.friend.dto.userDTO.UserResponseDTO;
import br.com.secret.friend.dto.userDTO.UserUpdateDTO;
import br.com.secret.friend.infra.exceptions.UserNotFoundException;
import br.com.secret.friend.model.User;
import br.com.secret.friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Transactional
    public UserResponseDTO createUser(UserRequesterDTO userDto) {
        User user = new User(userDto);
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        User userSaved = userRepository.save(user);
        return new UserResponseDTO(userSaved);
    }

    public List<UserResponseDTO> findAllUsers(){
        return converterToDTO(userRepository.findAll());
    }

    public UserResponseDTO findById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> converterToDTO(List<User> users){
        return users.stream()
                    .map(u -> new UserResponseDTO(u.getIdUser(),u.getName(), u.getAvatarUrl()))
                    .collect(Collectors.toList());
    }

    @Transactional
    public Boolean deleteById(Long userId){
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public UserResponseDTO updateUser(UserUpdateDTO userDto) {
        User userUpdate = userRepository.findById(userDto.userId())
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        userUpdate.updateInformation(userDto);
        if(userDto.password() != null){
            userUpdate.setHashedPassword(passwordEncoder.encode(userDto.password()));
        }
        return new UserResponseDTO(userUpdate);
    }

    @Transactional
    public void updateAvatar(Long userId, MultipartFile avatar) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));

        String oldFileName = user.getAvatarUrl();
        String extension = FilenameUtils.getExtension(avatar.getOriginalFilename());
        String newFileName = UUID.randomUUID() + "." + extension;
        Path newFilePath = Paths.get(uploadDir, newFileName);

        try {
            Files.copy(avatar.getInputStream(), newFilePath);
            user.setAvatarUrl(newFileName);

            if (oldFileName != null && !oldFileName.isEmpty()) {
                Path oldFilePath = Paths.get(uploadDir, oldFileName);
                Files.deleteIfExists(oldFilePath);
            }

        } catch (IOException e) {
            throw new RuntimeException("error to save avatar", e);
        }
    }

    @Transactional
    public void deleteAvatar(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Invalid user"));

        String nomeDoArquivo = user.getAvatarUrl();

        if (nomeDoArquivo != null && !nomeDoArquivo.isEmpty()) {
            try {
                Path pathDoArquivo = Paths.get(uploadDir, nomeDoArquivo);
                Files.deleteIfExists(pathDoArquivo);
                user.setAvatarUrl(null);

            } catch (IOException e) {
                throw new RuntimeException("Error delete avatar", e);
            }
        }
    }
}