package br.com.amigo.secreto.amigo.secreto.dto;

import br.com.amigo.secreto.amigo.secreto.model.User;

import java.time.LocalDateTime;

public class UserResponseDTO {
    private Long idUser;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String avatarUrl;

    public UserResponseDTO(Long idUser, String name, String email, LocalDateTime createdAt, String avatarUrl) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.avatarUrl = avatarUrl;
    }

    public UserResponseDTO(User user) {
        this.idUser = user.getIdUser();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.avatarUrl = user.getAvatarUrl();
    }
}
