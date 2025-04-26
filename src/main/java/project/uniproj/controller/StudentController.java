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
import project.uniproj.domain.Student;
import project.uniproj.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {

        log.info("Trying to add student {}", student);
        studentService.addStudent(student);
        log.info("Student successfully added");
        return new ResponseEntity<>("Student successfully added", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("Trying to get all students");
        List<Student> students = studentService.getStudents();
        if(students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Students successfully retrieved");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("Trying to get student {}", id);
        Student student = studentService.findById(id);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Student successfully retrieved");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        log.info("Trying to delete student {}", id);
        Student student = studentService.findById(id);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentService.deleteById(id);
        log.info("Student successfully deleted");
        return new ResponseEntity<>("Student successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        log.info("Trying to update student {}", id);
        if(studentService.updateStudent(id, student)){
            log.info("Student successfully updated");
            return new ResponseEntity<>("Student successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not updated", HttpStatus.BAD_REQUEST);
    }
}
