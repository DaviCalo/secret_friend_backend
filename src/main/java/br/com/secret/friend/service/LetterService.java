package br.com.secret.friend.service;

import br.com.secret.friend.dto.letterDTO.LetterRequesterDTO;
import br.com.secret.friend.dto.letterDTO.LetterResponseDTO;
import br.com.secret.friend.infra.exceptions.UserGroupNotFoundException;
import br.com.secret.friend.model.Letter;
import br.com.secret.friend.model.UserGroup;
import br.com.secret.friend.repository.LetterRepository;
import br.com.secret.friend.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Transactional
    public LetterResponseDTO registerLetter(LetterRequesterDTO groupAddUserDTO) {
        UserGroup userGroup = userGroupRepository.findById(groupAddUserDTO.idUserGroup())
                .orElseThrow(() -> new UserGroupNotFoundException("Invalid userGroup"));

        var letter = new Letter(groupAddUserDTO);
        letter.addUserGroup(userGroup);
        Letter letterSaved = letterRepository.save(letter);
        userGroup.addLetter(letterSaved);

        return new LetterResponseDTO(letterSaved);
    }

    public LetterResponseDTO getLetterByUserGroupID(Long userGroupId) {
        return userGroupRepository.findById(userGroupId)
                .flatMap(userGroup -> Optional.ofNullable(userGroup.getLetter()))
                .map(LetterResponseDTO::new)
                .orElseThrow(() -> new UserGroupNotFoundException("UserGroup or its Letter not found for the given ID"));
    }
}
