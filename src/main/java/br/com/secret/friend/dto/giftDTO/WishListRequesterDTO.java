package br.com.secret.friend.dto.giftDTO;

import jakarta.validation.constraints.NotNull;

public record WishListRequesterDTO(
        @NotNull
        Long giftId,
        @NotNull
        Long userGroupId
) { }
