package cz.project.myfit.repositories;

import cz.project.myfit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Check if email alraedy exists in db
    @Query("SELECT user FROM User user where user.email =?1")
    Optional<User> findUserByEmail(String email);
}
