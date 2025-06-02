package br.com.secret.friend.model;

import br.com.secret.friend.dto.groupDTO.GroupRequesterDTO;
import br.com.secret.friend.dto.groupDTO.GroupUpdateDTO;
import br.com.secret.friend.utils.StatusGroupEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "local")
    private String local;

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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserGroup> usersGroup = new HashSet<>();

    public Group(@Valid GroupRequesterDTO groupDto) {
        this.name = groupDto.name();
        this.description = groupDto.description();
        this.local = groupDto.local();
        this.drawDate = groupDto.drawDate();
        this.maximumValue = groupDto.maximumValue();
        this.minimumValue = groupDto.minimumValue();
    }

    public void addUsersGroup(UserGroup userGroup){
        this.getUsersGroup().add(userGroup);
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + idGroup +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", drawDate=" + drawDate +
                ", local='" + local + '\'' +
                ", statusGroup=" + statusGroup +
                ", maximumValue=" + maximumValue +
                ", minimumValue=" + minimumValue +
                ", user=" + user +
                ", usersGroup=" + usersGroup +
                '}';
    }

    public void updateGroup(@Valid GroupUpdateDTO groupDto) {
        if(groupDto.name() != null){
            this.name = groupDto.name();
        }

        if(groupDto.local() != null){
            this.local = groupDto.local();
        }

        if(groupDto.description() != null){
            this.description = groupDto.description();
        }

        if(groupDto.drawDate() != null){
            this.drawDate = groupDto.drawDate();
        }

        if(groupDto.maximumValue() != null){
            this.maximumValue = groupDto.maximumValue();
        }

        if(groupDto.minimumValue() != null){
            this.minimumValue = groupDto.minimumValue();
        }
    }
}