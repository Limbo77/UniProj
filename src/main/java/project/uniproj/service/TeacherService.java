package project.uniproj.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import project.uniproj.domain.Teacher;
import project.uniproj.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        if(teacherRepository.findAll().isEmpty()){
            throw new EntityNotFoundException("Could not retrieve teacher. No teacher present");
        }
        return teacherRepository.findAll();
    }


    public Teacher getTeacherById(Long id) {
        Optional<Teacher> user = teacherRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Could not retrieve teacher. No such teacher found");
        }
        return user.get();
    }


    public void addTeacher(Teacher teacher) {
        if(teacher.getEmail() == null || teacher.getPassword() == null){
            throw new IllegalArgumentException("Please provide a valid email and password");
        }
        teacherRepository.save(teacher);
    }

    public boolean updateTeacher(Long id, Teacher teacher) {

        if(teacher == null || teacher.getEmail() == null || teacher.getPassword() == null){
            throw new IllegalArgumentException("Please provide a valid email and password");
        }

        Optional<Teacher> currentTeacher = teacherRepository.findById(id);

        if(currentTeacher.isEmpty()){
            throw new EntityNotFoundException("Could not retrieve teacher. No teacher present");
        }
        currentTeacher.get().setFirstName(teacher.getFirstName());
        currentTeacher.get().setLastName(teacher.getLastName());
        currentTeacher.get().setPassword(teacher.getPassword());
        currentTeacher.get().setEmail(teacher.getEmail());
        currentTeacher.get().setPhone(teacher.getPhone());
        teacherRepository.save(currentTeacher.get());
        return true;

    }
}
