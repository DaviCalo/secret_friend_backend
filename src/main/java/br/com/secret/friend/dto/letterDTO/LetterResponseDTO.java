package br.com.secret.friend.dto.letterDTO;

import br.com.secret.friend.model.Letter;

public record LetterResponseDTO(
        Long idUserGroup,
        Long idLetter,
        String message
) {
    public LetterResponseDTO(Letter letter){
        this(letter.getUserGroup().getIdUserGroup(), letter.getIdLetter(), letter.getMessage());
    }
}
