package project.uniproj.service;

import org.springframework.stereotype.Service;
import project.uniproj.domain.Student;
import project.uniproj.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        if(student.getEmail().isEmpty() || student.getPassword().isEmpty()){
            throw new IllegalArgumentException("Email or password cannot be empty");
        }
        studentRepository.save(student);
    }


    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            throw new IllegalArgumentException("There are no students in the database");
        }
        return students;
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new IllegalArgumentException("There is no student with id " + id);
        }
        return student.orElse(null);
    }

    public void deleteById(Long id) {
        if(studentRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("There is no student with id " + id);
        }
        studentRepository.deleteById(id);
    }

    public boolean updateStudent(Long id, Student student) {

        if(student == null || student.getEmail() == null || student.getPassword() == null){
            throw new IllegalArgumentException("Please provide a valid email and password");
        }

        Optional<Student> currentStudent = studentRepository.findById(id);

        if(currentStudent.isEmpty()){
            throw new IllegalArgumentException("Student with id " + id + " not found");
        }

        currentStudent.get().setFirstName(student.getFirstName());
        currentStudent.get().setLastName(student.getLastName());
        currentStudent.get().setEmail(student.getEmail());
        currentStudent.get().setPassword(student.getPassword());
        currentStudent.get().setPhone(student.getPhone());
        studentRepository.save(currentStudent.get());
        return true;

    }
}
