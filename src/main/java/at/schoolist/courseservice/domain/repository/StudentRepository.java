package at.schoolist.courseservice.domain.repository;

import at.schoolist.courseservice.domain.model.Student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
}