package cz.project.myfit.repository;

import cz.project.myfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);

    Optional<User> findUserByName(String name);
}
