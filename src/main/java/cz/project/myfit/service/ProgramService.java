package cz.project.myfit.service;

import cz.project.myfit.DTO.ProgramDTO;
import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import cz.project.myfit.repository.ProgramRepository;
import cz.project.myfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository, UserRepository userRepository) {
        this.programRepository = programRepository;
        this.userRepository = userRepository;
    }

    public List<Program> getProgramsByUser(User user) {
        return programRepository.findProgramsByUser(user);
    }

    // Add a program to a user
    public Program addProgramToUser(Long id, ProgramDTO programDTO) {
        User user = userRepository.findUserById(id).orElse(null);
        if (user != null && programDTO != null && programDTO.getName() != null) {
            Program program = new Program(programDTO.getName(), user, LocalDate.now());
            programRepository.save(program);
            return program;
        } else {
            return null;
        }
    }

    // Delete a program
    public void deleteProgram(Long programId) {
        programRepository.deleteById(programId);
    }
}
