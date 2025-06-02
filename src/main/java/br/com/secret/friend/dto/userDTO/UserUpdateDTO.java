package br.com.secret.friend.dto.userDTO;

import br.com.secret.friend.utils.AuthProviderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record UserUpdateDTO(
        @NotNull()
        Long userId,
        String name,
        String avatarUrl,
        @Email()
        String email,
        @Size(min = 8)
        String password,
        BigInteger phoneNumber,
        AuthProviderEnum authProvider,
        String idGoogle
) { }