package br.com.amigo.secreto.amigo.secreto.dto;

public record UserResponseDTO(
        Long idUser,
        String name,
        String email,
        String avatarUrl
) {}