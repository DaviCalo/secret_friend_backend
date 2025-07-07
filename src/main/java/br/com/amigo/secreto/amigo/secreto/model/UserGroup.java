package br.com.amigo.secreto.amigo.secreto.model;

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

    @ManyToOne()
    @JoinColumn(name = "sender_user_id", nullable = false)
    private User idSenderUser;

    @ManyToOne()
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne()
    @JoinColumn(name = "recipient_user_id")
    private User idRecipientUser;

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
}
