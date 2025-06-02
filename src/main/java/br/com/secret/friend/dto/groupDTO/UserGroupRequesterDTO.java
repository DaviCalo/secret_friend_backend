package br.com.secret.friend.dto.groupDTO;

import jakarta.validation.constraints.NotNull;

public record UserGroupRequesterDTO(
        @NotNull
        Long groupId
) { }
