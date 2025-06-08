package br.com.amigo.secreto.amigo.secreto.service;
import br.com.amigo.secreto.amigo.secreto.model.User;
import br.com.amigo.secreto.amigo.secreto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}