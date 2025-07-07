package br.com.amigo.secreto.amigo.secreto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "gifts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idGift")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_id")
    private Long idGift;

    @Column(name = "gift_name")
    private String giftName;

    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "idGiftSelect", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserGroup> userGroup;
}