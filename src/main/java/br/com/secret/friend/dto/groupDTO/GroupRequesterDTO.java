package br.com.secret.friend.dto.groupDTO;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GroupRequesterDTO(
        @NotBlank()
        String name,
        String local,
        String description,
        LocalDate drawDate,
        BigDecimal maximumValue,
        BigDecimal minimumValue
) {}