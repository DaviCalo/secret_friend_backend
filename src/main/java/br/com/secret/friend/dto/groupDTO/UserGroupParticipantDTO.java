package br.com.secret.friend.dto.groupDTO;

import br.com.secret.friend.model.UserGroup;

public record UserGroupParticipantDTO(
    Long senderUser,
    Long recipientUser
) {
    public UserGroupParticipantDTO(UserGroup userGroup){
        this(
            (userGroup.getSenderUser() == null) ? null: userGroup.getSenderUser().getIdUser(),
            (userGroup.getRecipientUser() == null) ? null: userGroup.getRecipientUser().getIdUser()
        );
    }
}