package br.com.amigo.secreto.amigo.secreto.dto.userDTO;

import br.com.amigo.secreto.amigo.secreto.model.User;

public record UserResponseDTO(
        Long idUser,
        String name,
        String avatarUrl
) {
    public UserResponseDTO(User user){
        this(user.getIdUser(),user.getName(), user.getAvatarUrl());
    }
}