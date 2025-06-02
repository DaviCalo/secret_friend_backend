package br.com.secret.friend.dto.groupDTO;

import br.com.secret.friend.model.Group;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GroupResponseDTO(
    Long idGroup,
    String name,
    String description,
    LocalDate drawDate,
    String local,
    BigDecimal maximumValue,
    BigDecimal minimumValue,
    Long creatorUserId
) {
    public GroupResponseDTO(Group group){
        this(
            group.getIdGroup(),
            group.getName(),
            group.getDescription(),
            group.getDrawDate(),
            group.getLocal(),
            group.getMaximumValue(),
            group.getMinimumValue(),
            group.getUser().
            getIdUser()
        );
    }
}