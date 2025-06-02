package br.com.secret.friend.dto.groupDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GroupUpdateDTO(
    String name,
    String local,
    String description,
    LocalDate drawDate,
    BigDecimal maximumValue,
    BigDecimal minimumValue
) {}