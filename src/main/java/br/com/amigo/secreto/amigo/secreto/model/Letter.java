package br.com.amigo.secreto.amigo.secreto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "letters")
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