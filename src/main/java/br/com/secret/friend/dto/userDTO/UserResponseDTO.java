package br.com.secret.friend.dto.userDTO;

import br.com.secret.friend.model.User;

public record UserResponseDTO(
    Long idUser,
    String name,
    String avatarUrl
) {
    public UserResponseDTO(User user){
        this(user.getIdUser(),user.getName(), user.getAvatarUrl());
    }
}