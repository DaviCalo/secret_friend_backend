package br.com.secret.friend.controller;

import br.com.secret.friend.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@SecurityRequirement(name = "bearer-key")
public class GroupController {

    @Autowired
    private GroupService groupService;

//    @PostMapping("/create")
//    public ResponseEntity<String> registerGroup(@Valid @RequestBody GroupRegisterDTO userDto) {
//        Group newGroup = new Group.Builder()
//                .user(new User())
//                .name(userDto.getName())
//                .drawDate(userDto.getDrawDate())
//                .description(userDto.getDescription())
//                .maximumValue(userDto.getMaximumValue())
//                .minimumValue(userDto.getMinimumValue())
//                .statusGroup(StatusGroupEnum.ACTIVE)
//                .build();
//
//        newGroup.getUser().setIdUser(userDto.getCreatorUserId());
//
//        try {
//            Group groupSave = groupService.registerGroup(newGroup);
//            return new ResponseEntity<>("Grupo '" + groupSave.getName() + "' criado com sucesso! ID: " + groupSave.getIdGroup(), HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>("Erro ao criar grupo: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}