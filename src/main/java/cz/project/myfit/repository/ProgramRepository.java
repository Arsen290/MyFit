package cz.project.myfit.repository;

import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
    Optional<Program> findProgramById(Long id);
    List<Program> findProgramsByUser(User user);

}
