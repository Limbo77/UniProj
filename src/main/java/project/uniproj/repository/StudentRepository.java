package project.uniproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.uniproj.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
