package at.schoolist.courseservice.domain.repository;

import at.schoolist.courseservice.domain.model.Class;
import at.schoolist.courseservice.domain.model.Enrollment;
import at.schoolist.courseservice.domain.model.Student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
  void deleteEnrollmentByStudentAndEnrollmentForClass(Student student, Class enrollmentForClass);
}