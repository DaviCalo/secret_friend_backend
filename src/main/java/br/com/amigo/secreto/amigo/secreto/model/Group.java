package br.com.amigo.secreto.amigo.secreto.model;

import br.com.amigo.secreto.amigo.secreto.utils.AuthProviderEnum;
import br.com.amigo.secreto.amigo.secreto.utils.StatusGroupEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long idGroup;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "draw_date")
    private LocalDate drawDate;

    @ManyToOne()
    @JoinColumn(name = "creator_user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_group")
    private StatusGroupEnum statusGroup;

    @Column(name = "maximum_value")
    private BigDecimal maximumValue;

    @Column(name = "minimum_value")
    private BigDecimal minimumValue;

    private Group() {}

    private Group(Group.Builder builder) {
        this.name = builder.name;
        this.idGroup = builder.idGroup;
        this.description = builder.description;
        this.drawDate = builder.drawDate;
        this.user = builder.user;
        this.statusGroup = builder.statusGroup;
        this.maximumValue = builder.maximumValue;
        this.minimumValue = builder.minimumValue;
    }

    public static class Builder {
        private Long idGroup;
        private String name;
        private String description;
        private LocalDate drawDate;
        private User user;
        private StatusGroupEnum statusGroup;
        private BigDecimal maximumValue;
        private BigDecimal minimumValue;

        public Builder idGroup(Long idGroup) {
            this.idGroup = idGroup;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder drawDate(LocalDate drawDate) {
            this.drawDate = drawDate;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder statusGroup(StatusGroupEnum statusGroup) {
            this.statusGroup = statusGroup;
            return this;
        }

        public Builder maximumValue(BigDecimal maximumValue) {
            this.maximumValue = maximumValue;
            return this;
        }

        public Builder minimumValue(BigDecimal minimumValue) {
            this.minimumValue = minimumValue;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDate drawDate) {
        this.drawDate = drawDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusGroupEnum getStatusGroup() {
        return statusGroup;
    }

    public void setStatusGroup(StatusGroupEnum statusGroup) {
        this.statusGroup = statusGroup;
    }

    public BigDecimal getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(BigDecimal maximumValue) {
        this.maximumValue = maximumValue;
    }

    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(BigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }
}