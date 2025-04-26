package project.uniproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.uniproj.domain.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
