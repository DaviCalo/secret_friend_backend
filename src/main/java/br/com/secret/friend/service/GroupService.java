package br.com.secret.friend.service;

import br.com.secret.friend.dto.groupDTO.*;
import br.com.secret.friend.infra.exceptions.GroupNotFoundException;
import br.com.secret.friend.infra.exceptions.UserNotFoundException;
import br.com.secret.friend.model.Group;
import br.com.secret.friend.model.User;
import br.com.secret.friend.repository.GroupRepository;
import br.com.secret.friend.repository.UserRepository;
import br.com.secret.friend.utils.StatusGroupEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupService userGroupService;

    @Transactional
    public GroupResponseDTO registerGroup(GroupRequesterDTO groupDto, Authentication authentication) {
        User userId = (User) authentication.getPrincipal();
        User user = userRepository.findById(userId.getIdUser())
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        Group group = new Group(groupDto);
        group.setStatusGroup(StatusGroupEnum.ACTIVE);
        group.setUser(user);

        Group savedGroup = groupRepository.save(group);
        user.setGroupsCreated(group);
        return userGroupService.registerUserGroup(new UserGroupRequesterDTO(savedGroup.getIdGroup()), authentication);
    }

    public GroupResponseDTO findByGroupId(Long id){
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        return new GroupResponseDTO(group);
    }

    public List<GroupResponseDTO> findByUserIdAllGroupCreated(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
        Set<Group> groups = user.getGroupsCreated();
        return converterSetToDTO(groups);
    }

//    public List<GroupResponseDTO> findByUserIdAllGroupParticipating(Long userId){
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
//        Set<Group> groups = user.getUserGroups();
//        return converterSetToDTO(groups);
//    }

    public List<GroupResponseDTO> findAllGroups() {
        return converterToDTO(groupRepository.findAll());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException("Invalid group");
        }
        groupRepository.deleteById(id);
    }

    public List<GroupResponseDTO> converterToDTO(List<Group> Group){
        return Group.stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<GroupResponseDTO> converterSetToDTO(Set<Group> Group){
        return Group.stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public GroupResponseDTO updateGroup(@Valid GroupUpdateDTO groupDto, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Invalid group"));
        group.updateGroup(groupDto);
        return new GroupResponseDTO(group);
    }
}