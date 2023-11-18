package cz.project.myfit.service;

import cz.project.myfit.model.Program;
import cz.project.myfit.model.User;
import cz.project.myfit.repository.ProgramRepository;
import cz.project.myfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> getProgramsByUser(User user) {
        return programRepository.findProgramsByUser(user);
    }
}
