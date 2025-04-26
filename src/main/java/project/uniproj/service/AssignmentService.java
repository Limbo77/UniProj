package project.uniproj.service;

import org.springframework.stereotype.Service;
import project.uniproj.domain.Assignment;
import project.uniproj.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    AssignmentService(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    public void addAssignment(Assignment assignment) {

        if(assignment == null){
            throw new IllegalArgumentException("Assignment cannot be null");
        }

        assignmentRepository.save(assignment);

    }


    public List<Assignment> getAssignments() {

        List<Assignment> assignments = assignmentRepository.findAll();
        if(assignments.isEmpty()){
            throw new IllegalArgumentException("No assignment found");
        }

        return assignments;

    }

    public Assignment findById(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if(assignment.isEmpty()){
            throw new IllegalArgumentException("Assignment not found");
        }
        return assignment.get();
    }

    public boolean updateAssignment(Long id, Assignment assignment) {
        if(assignment == null){
            throw new IllegalArgumentException("Assignment cannot be null");
        }

        Optional<Assignment> currentAssignment = assignmentRepository.findById(id);

        if(currentAssignment.isEmpty()){
            throw new IllegalArgumentException("Assignment not found");
        }

        currentAssignment.get().setTitle(assignment.getTitle());
        currentAssignment.get().setDescription(assignment.getDescription());
        assignmentRepository.save(currentAssignment.get());
        return true;

    }
}
