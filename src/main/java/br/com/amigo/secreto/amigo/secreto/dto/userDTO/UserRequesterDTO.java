package br.com.amigo.secreto.amigo.secreto.dto.userDTO;

import br.com.amigo.secreto.amigo.secreto.utils.AuthProviderEnum;
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