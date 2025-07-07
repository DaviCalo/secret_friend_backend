package br.com.amigo.secreto.amigo.secreto.model;

import br.com.amigo.secreto.amigo.secreto.utils.StatusGroupEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idGroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long idGroup;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "draw_date")
    private LocalDate drawDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_group")
    private StatusGroupEnum statusGroup;

    @Column(name = "maximum_value")
    private BigDecimal maximumValue;

    @Column(name = "minimum_value")
    private BigDecimal minimumValue;

    @ManyToOne()
    @JoinColumn(name = "creator_user_id")
    private User user;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserGroup> userGroup; // TODO: PRECISO ADICIONAR O = new ArrayList<>()?
}