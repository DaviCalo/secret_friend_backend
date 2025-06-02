package br.com.secret.friend.dto.groupDTO;


import java.util.List;

public record UserGroupOnlyGroupDTO(
    List<GroupResponseDTO> group
) { }
