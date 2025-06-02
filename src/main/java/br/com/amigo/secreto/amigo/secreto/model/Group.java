package br.com.amigo.secreto.amigo.secreto.model;

import br.com.amigo.secreto.amigo.secreto.utils.StatusGroupEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long idGroup;

    private String name;

    private String description;

    @Column(name = "draw_date")
    private LocalDate drawDate;

    @Column(name = "creator_user_id")
    private Long creatorUserId; // tem que concetar ao usario

    @Column(name = "status_group")
    private StatusGroupEnum statusGroup;

    @Column(name = "maximum_value")
    private BigDecimal maximumValue;

    @Column(name = "minimum_value")
    private BigDecimal minimumValue;
}
