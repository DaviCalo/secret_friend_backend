package br.com.secret.friend.dto.groupDTO;

import jakarta.validation.constraints.NotNull;

public record GroupAddUserDTO(
        @NotNull
        Long creatorUserId,
        @NotNull
        Long groupId
) { }
