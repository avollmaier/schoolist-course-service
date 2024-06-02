package at.schoolist.courseservice.domain.repository;

import at.schoolist.courseservice.domain.model.Subject;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}