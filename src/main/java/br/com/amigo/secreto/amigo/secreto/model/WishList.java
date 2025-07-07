package br.com.amigo.secreto.amigo.secreto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wish_lists")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idWishList")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_list_id")
    private Long idWishList;

    @ManyToOne
    @JoinColumn(name = "gift_id", nullable = false)
    private Gift gift;

    @OneToOne
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;
}