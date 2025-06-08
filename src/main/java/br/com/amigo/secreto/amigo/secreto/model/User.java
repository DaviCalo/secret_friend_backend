package br.com.amigo.secreto.amigo.secreto.model;

import br.com.amigo.secreto.amigo.secreto.utils.AuthProviderEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider",nullable = false)
    private AuthProviderEnum authProvider;

    @Column(nullable = false)
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "id_google")
    private String idGoogle;

    @Column(name = "phone_number")
    private BigInteger phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Group> created_groups; //ver se ta certo

    public User() {}

    private User(Builder builder) {
        this.email = builder.email;
        this.hashedPassword = builder.hashedPassword;
        this.authProvider = builder.authProvider;
        this.name = builder.name;
        this.avatarUrl = builder.avatarUrl;
        this.idGoogle = builder.idGoogle;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder {
        private String email;
        private String hashedPassword;
        private AuthProviderEnum authProvider;
        private String name;
        private String avatarUrl;
        private String idGoogle;
        private BigInteger phoneNumber;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder hashedPassword(String hashedPassword) {
            this.hashedPassword = hashedPassword;
            return this;
        }

        public Builder authProvider(AuthProviderEnum authProvider) {
            this.authProvider = authProvider;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder idGoogle(String idGoogle) {
            this.idGoogle = idGoogle;
            return this;
        }

        public Builder phoneNumber(BigInteger phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public AuthProviderEnum getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProviderEnum authProvider) {
        this.authProvider = authProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", authProvider=" + authProvider +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", idGoogle='" + idGoogle + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}