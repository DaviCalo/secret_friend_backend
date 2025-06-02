package br.com.secret.friend.service;

import br.com.secret.friend.dto.groupDTO.*;
import br.com.secret.friend.infra.exceptions.GroupNotFoundException;
import br.com.secret.friend.infra.exceptions.UserNotFoundException;
import br.com.secret.friend.model.Group;
import br.com.secret.friend.model.User;
import br.com.secret.friend.model.UserGroup;
import br.com.secret.friend.repository.GroupRepository;
import br.com.secret.friend.repository.UserGroupRepository;
import br.com.secret.friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public GroupResponseDTO registerUserGroup(UserGroupRequesterDTO userGroupDto, Authentication authentication) {
        User userId = (User) authentication.getPrincipal();
        User user = userRepository.findById(userId.getIdUser())
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        Group group = groupRepository.findById(userGroupDto.groupId())
                .orElseThrow(() -> new GroupNotFoundException("Invalid group"));

        UserGroup userGroupSaved = userGroupRepository.save(new UserGroup(group, user));
        group.addUsersGroup(userGroupSaved);
        user.addSenderUsersGroup(userGroupSaved);

        return new GroupResponseDTO(group);
    }

    public UserGroupOnlyGroupDTO findByUserIdAllGroupParticipating(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        List<UserGroup> userGroups = userGroupRepository.findBySenderUser(user);

        return converterToGroupDTO(userGroups);
    }

    public List<UserGroupParticipantDTO> getAllParticipant(Long groupID) {
        Group group = groupRepository.findById(groupID)
                .orElseThrow(() -> new GroupNotFoundException("Invalid group"));
        List<UserGroup> userGroups = userGroupRepository.findByGroup(group);
        return converterToDTO(userGroups);
    }

    public UserGroupOnlyGroupDTO converterToGroupDTO(List<UserGroup> listUserGroup){
        List<GroupResponseDTO> groupDTOs = listUserGroup.stream()
                .map(UserGroup::getGroup)
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList());

        return new UserGroupOnlyGroupDTO(groupDTOs);
    }

    public List<UserGroupParticipantDTO> converterToDTO(List<UserGroup> userGroups){
        return userGroups.stream()
                .map(UserGroupParticipantDTO::new)
                .collect(Collectors.toList());
    }
}
