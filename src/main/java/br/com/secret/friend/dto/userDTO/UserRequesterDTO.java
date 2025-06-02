package br.com.secret.friend.dto.userDTO;

import br.com.secret.friend.utils.AuthProviderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record UserRequesterDTO(
        @NotBlank()
        String name,
        @NotBlank()
        @Email()
        String email,
        @NotBlank()
        @Size(min = 8)
        String password,
        BigInteger phoneNumber,
        @NotNull
        AuthProviderEnum authProvider,
        String idGoogle
) {}