package br.com.amigo.secreto.amigo.secreto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroupRegisterDTO {
    @NotNull
    private Long creatorUserId;

    @NotBlank()
    private String name;

    private String description;

    private LocalDate drawDate;

    private BigDecimal maximumValue;

    private BigDecimal minimumValue;

    public GroupRegisterDTO(String name, String description, LocalDate drawDate, BigDecimal maximumValue, Long creatorUserId, BigDecimal minimumValue) {
        this.name = name;
        this.description = description;
        this.drawDate = drawDate;
        this.maximumValue = maximumValue;
        this.creatorUserId = creatorUserId;
        this.minimumValue = minimumValue;
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

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }
}
