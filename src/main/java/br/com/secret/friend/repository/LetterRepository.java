package br.com.secret.friend.repository;

import br.com.secret.friend.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository  extends JpaRepository<Letter, Long> {
}
