package br.com.secret.friend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "letters")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idLetter")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long idLetter;

    @Column(name = "message")
    private String message;

    @OneToOne
    //@MapsId // TODO: SER SE PRECISA MESMO
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;
}