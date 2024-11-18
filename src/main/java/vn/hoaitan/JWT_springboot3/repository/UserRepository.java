package vn.hoaitan.JWT_springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoaitan.JWT_springboot3.entity.User;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
