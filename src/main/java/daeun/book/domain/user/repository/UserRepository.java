package daeun.book.domain.user.repository;

import daeun.book.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속받아야 .save() 같은 데이터베이스 메소드를 사용할 수 있습니다.
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
