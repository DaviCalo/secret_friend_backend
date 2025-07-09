package br.com.secret.friend.service;

import br.com.secret.friend.model.Group;
import br.com.secret.friend.model.User;
import br.com.secret.friend.repository.GroupRepository;
import br.com.secret.friend.repository.UserRepository;
import br.com.secret.friend.utils.StatusGroupEnum;
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