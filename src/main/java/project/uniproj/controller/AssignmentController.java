package project.uniproj.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.uniproj.domain.Assignment;
import project.uniproj.service.AssignmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/assignment")
@Slf4j
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/add")
    public ResponseEntity<String> addAssignment(@RequestBody Assignment assignment) {
        log.info("Trying to add assignment {}", assignment);
        assignmentService.addAssignment(assignment);
        log.info("Successfully added assignment {}", assignment);
        return new ResponseEntity<>("Successfully added assignment", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        log.info("Trying to get all assignments");
        List<Assignment> assignments = assignmentService.getAssignments();
        if(assignments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Successfully retrieved all assignments");
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable("id") Long id) {
        log.info("Trying to get assignment {}", id);
        Assignment assignment = assignmentService.findById(id);
        if(assignment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Successfully retrieved assignment {}", assignment);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignmentById(@PathVariable("id") Long id) {
        log.info("Trying to delete assignment {}", id);
        Assignment assignment = assignmentService.findById(id);
        if(assignment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Successfully deleted assignment {}", id);
        return new ResponseEntity<>("Successfully deleted assignment", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAssignmentById(@PathVariable("id") Long id, @RequestBody Assignment assignment) {
        log.info("Trying to update assignment {}", id);
        if(assignmentService.updateAssignment(id, assignment)){
            log.info("Successfully updated assignment");
            return new ResponseEntity<>("Successfully updated assignment", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update assignment", HttpStatus.BAD_REQUEST);
    }

}
