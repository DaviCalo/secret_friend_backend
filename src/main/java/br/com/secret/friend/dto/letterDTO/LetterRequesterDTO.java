package br.com.secret.friend.dto.letterDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LetterRequesterDTO(
        @NotNull
        Long idUserGroup,
        @NotBlank
        String message
) { }
