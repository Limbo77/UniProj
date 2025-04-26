package project.uniproj.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import project.uniproj.domain.Teacher;
import project.uniproj.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        log.info("Trying to fetch all teachers");
        if(teacherService.getTeachers().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers present");
        }
        log.info("All teachers have been fetched");
        return new ResponseEntity<>(teacherService.getTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getUserById(@PathVariable Long id) {
        log.info("Trying to fetch teacher by id");
        if(teacherService.getTeacherById(id) == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No teacher found");
        }
        log.info("Teacher fetched with id {}", id);
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody Teacher teacher) {
        log.info("Trying to create new teacher");
        teacherService.addTeacher(teacher);
        log.info("Teacher successfully created");
        return new ResponseEntity<>("Teacher successfully created",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody Teacher teacher) {
        log.info("Trying to update teacher with id {}", id);
        if(teacherService.updateTeacher(id, teacher)){
            log.info("Teacher successfully updated");
            return new ResponseEntity<>("Teacher successfully updated",HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No teacher found");
    }
}
