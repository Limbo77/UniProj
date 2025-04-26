package project.uniproj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.uniproj.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
