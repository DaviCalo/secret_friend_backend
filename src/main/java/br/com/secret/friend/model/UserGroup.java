package br.com.secret.friend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_groups")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUserGroup")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long idUserGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id", nullable = false)
    @JsonBackReference
    private User senderUser;

    @ManyToOne()
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_user_id")
    private User recipientUser;

    @ManyToOne()
    @JoinColumn(name = "gift_select_id")
    private Gift idGiftSelect;

    @OneToOne(mappedBy = "userGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Letter letter;

    @OneToOne(mappedBy = "userGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private WishList wishlist;

    @CreationTimestamp
    @Column(name = "entry_date")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserGroup(Group group, User user) {
        this.group = group;
        this.senderUser = user;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "idUserGroup=" + idUserGroup +
                ", idSenderUser=" + senderUser +
                ", group=" + group +
                ", recipientUser=" + recipientUser +
                ", idGiftSelect=" + idGiftSelect +
                ", letter=" + letter +
                ", wishlist=" + wishlist +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void addLetter(Letter letter) {
        this.setLetter(letter);
    }
}
