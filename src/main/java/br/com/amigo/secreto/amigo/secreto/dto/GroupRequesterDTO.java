package br.com.amigo.secreto.amigo.secreto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GroupRequesterDTO(
        @NotNull
        Long creatorUserId,
        @NotBlank()
        String name,
        String description,
        LocalDate drawDate,
        BigDecimal maximumValue,
        BigDecimal minimumValue
) {}