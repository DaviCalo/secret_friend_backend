package br.com.secret.friend.repository;

import br.com.secret.friend.model.Group;
import br.com.secret.friend.model.User;
import br.com.secret.friend.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @Query("SELECT e FROM UserGroup e WHERE e.group = :group")
    List<UserGroup> findByGroup(@Param("group") Group group);

    @Query("SELECT e FROM UserGroup e WHERE e.senderUser = :user")
    List<UserGroup> findBySenderUser(User user);
}