package br.com.amigo.secreto.amigo.secreto.repository;

import br.com.amigo.secreto.amigo.secreto.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}