package br.com.amigo.secreto.amigo.secreto.dto;

import br.com.amigo.secreto.amigo.secreto.utils.AuthProviderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public class UserRegisterDTO {
    @NotBlank()
    private String name;

    @NotBlank()
    @Email()
    private String email;

    @NotBlank()
    @Size(min = 8)
    private String password;

    @NotBlank()
    private BigInteger phoneNumber;

    @NotNull
    private AuthProviderEnum authProvider;

    private String idGoogle;
    private String avatarUrl;

    public UserRegisterDTO(String name, String email, String password, String avatarUrl) {
        this.name = name != null ? name.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.password = password != null ? password.trim() : null;
        this.avatarUrl = avatarUrl != null ? avatarUrl.trim() : null;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AuthProviderEnum getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProviderEnum authProvider) {
        this.authProvider = authProvider;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", authProvider=" + authProvider +
                ", idGoogle='" + idGoogle + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
