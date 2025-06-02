package br.com.secret.friend.model;

import br.com.secret.friend.dto.letterDTO.LetterRequesterDTO;
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
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;

    public Letter(LetterRequesterDTO letterRequesterDTO){
        this.message = letterRequesterDTO.message();
    }

    public void addUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}