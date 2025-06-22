package br.com.amigo.secreto.amigo.secreto.service;

import br.com.amigo.secreto.amigo.secreto.model.Group;
import br.com.amigo.secreto.amigo.secreto.model.User;
import br.com.amigo.secreto.amigo.secreto.repository.GroupRepository;
import br.com.amigo.secreto.amigo.secreto.repository.UserRepository;
import br.com.amigo.secreto.amigo.secreto.utils.StatusGroupEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Group registerGroup(Group group) {
        User user = userRepository.findById(group.getUser().getIdUser())
                .orElseThrow(() -> new RuntimeException("Criador do grupo não encontrado."));
        group.setUser(user);
        group.setStatusGroup(StatusGroupEnum.ACTIVE);
        return groupRepository.save(group);
    }
}