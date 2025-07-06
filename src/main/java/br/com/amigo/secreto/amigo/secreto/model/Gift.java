package br.com.amigo.secreto.amigo.secreto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gifts")
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

    Gift(){}

    public Long getIdGift() {
        return idGift;
    }

    public void setIdGift(Long idGift) {
        this.idGift = idGift;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
}