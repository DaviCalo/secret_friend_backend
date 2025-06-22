package br.com.amigo.secreto.amigo.secreto.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record UserDTO(
    String email,
    String name,
    String avatarUrl,
    String idGoogle,
    BigInteger phoneNumber,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
