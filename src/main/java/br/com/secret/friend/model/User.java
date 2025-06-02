package br.com.secret.friend.model;

import br.com.secret.friend.dto.userDTO.UserRequesterDTO;
import br.com.secret.friend.dto.userDTO.UserUpdateDTO;
import br.com.secret.friend.utils.AuthProviderEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Group> groupsCreated =  new HashSet<>();

    @OneToMany(mappedBy = "senderUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<UserGroup> userGroupsSender =  new HashSet<>();

    @OneToMany(mappedBy = "recipientUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserGroup> userGroupsRecipient =  new HashSet<>();

    public User(UserRequesterDTO userDto) {
        this.email = userDto.email();
        this.hashedPassword = userDto.password();
        this.authProvider = userDto.authProvider();
        this.name = userDto.name();
        this.idGoogle = userDto.idGoogle();
        this.phoneNumber = userDto.phoneNumber();
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

    public void updateInformation(UserUpdateDTO userDto) {
        if (userDto.email() != null) {
            this.email = userDto.email();
        }
        if (userDto.authProvider() != null) {
            this.authProvider = userDto.authProvider();
        }
        if (userDto.name() != null) {
            this.name = userDto.name();
        }
        if (userDto.avatarUrl() != null) {
            this.avatarUrl = userDto.avatarUrl();
        }
        if (userDto.idGoogle() != null) {
            this.idGoogle = userDto.idGoogle();
        }
        if (userDto.phoneNumber() != null) {
            this.phoneNumber = userDto.phoneNumber();
        }
    }

    public void setGroupsCreated(Group group){
        this.getGroupsCreated().add(group);
    }

    public void addSenderUsersGroup(UserGroup userGroup){
        this.getUserGroupsSender().add(userGroup);
    }

    public void addRecipientUsersGroup(UserGroup userGroup){
        this.getUserGroupsRecipient().add(userGroup);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }
}