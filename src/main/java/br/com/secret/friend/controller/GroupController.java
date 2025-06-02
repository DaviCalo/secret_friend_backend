package br.com.secret.friend.controller;

import br.com.secret.friend.dto.groupDTO.*;
import br.com.secret.friend.service.GroupService;
import br.com.secret.friend.service.UserGroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/group")
@SecurityRequirement(name = "bearer-key")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserGroupService userGroupService;

    @PostMapping()
    public ResponseEntity<GroupResponseDTO> registerGroup(@Valid @RequestBody GroupRequesterDTO groupDto,
                                                          Authentication authentication,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        GroupResponseDTO responseDto = groupService.registerGroup(groupDto, authentication);
        URI uri = uriComponentsBuilder
                .path("/api/group/{id}")
                .buildAndExpand(responseDto.idGroup())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @PutMapping("/update/{groupId}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@RequestBody @Valid GroupUpdateDTO groupDto, @PathVariable("groupId") Long groupId){
        GroupResponseDTO responseDto = groupService.updateGroup(groupDto, groupId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<GroupResponseDTO>> findAllUsers(){
        List<GroupResponseDTO> listGroups = groupService.findAllGroups();
        return ResponseEntity.ok(listGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> findById(@PathVariable Long id){
        GroupResponseDTO groupResponseDTO = groupService.findByGroupId(id);
        return ResponseEntity.ok(groupResponseDTO);
    }

    @PostMapping("/add/users")
    public ResponseEntity<GroupResponseDTO> addUserToGroup(@Valid @RequestBody UserGroupRequesterDTO userGroupDto,
                                                           Authentication authentication,
                                                           UriComponentsBuilder uriComponentsBuilder){
        GroupResponseDTO responseDto = userGroupService.registerUserGroup(userGroupDto, authentication); //TODO dar para puxar logo da usergroup
        URI uri = uriComponentsBuilder
                .path("/api/group/add/{id}")
                .buildAndExpand(responseDto.idGroup())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/all-participant/{groupID}")
    public ResponseEntity<List<UserGroupParticipantDTO>> findAllParticipantOfGroupId(@PathVariable("groupID") Long groupID){
        List<UserGroupParticipantDTO> userGroupResponseDTO = userGroupService.getAllParticipant(groupID);
        return ResponseEntity.ok(userGroupResponseDTO);
    }

    @GetMapping("/all-created-group/{userId}")
    public ResponseEntity<List<GroupResponseDTO>> findByUserIdAllGroupCreated(@PathVariable("userId") Long groupId){
        var userGroupResponseDTO = groupService.findByUserIdAllGroupCreated(groupId);
        return ResponseEntity.ok(userGroupResponseDTO);
    }

    @GetMapping("/all-participating-group/{userId}/users")
    public ResponseEntity<UserGroupOnlyGroupDTO> findByUserIdAllGroupParticipating(@PathVariable("userId") Long userId){
        var userGroupResponseDTO = userGroupService.findByUserIdAllGroupParticipating(userId);
        return ResponseEntity.ok(userGroupResponseDTO);
    }
}