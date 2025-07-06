package br.com.amigo.secreto.amigo.secreto.controller;

import br.com.amigo.secreto.amigo.secreto.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
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