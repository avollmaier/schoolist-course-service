package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.model.Enrollment;
import at.schoolist.courseservice.domain.repository.EnrollmentRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService extends CrudService<Enrollment, UUID> {

  private final EnrollmentRepository repository;

  private final StudentService studentService;

  private final ClassService classService;

  protected EnrollmentService(EnrollmentRepository repository, StudentService studentService, ClassService classService) {
    super(repository);
    this.repository = repository;
    this.studentService = studentService;
    this.classService = classService;
  }

  @Override
  public Enrollment update(UUID id, Enrollment entity) {
    return super.repository.findById(id)
               .map(existingEnrollment -> {
                 var enrollmentToUpdate = new Enrollment(
                     existingEnrollment.getId(),
                     existingEnrollment.getStudent(),
                     existingEnrollment.getEnrollmentForClass(),
                     entity.getEnrollmentDate(),
                     existingEnrollment.getCreatedAt(),
                     existingEnrollment.getUpdatedAt(),
                     existingEnrollment.getVersion()
                 );
                 return super.repository.save(enrollmentToUpdate);
               })
               .orElseGet(() -> create(entity));
  }

  public void deleteEnrollmentByStudentIdAndClassId(UUID studentId, UUID classId) {
    repository.deleteEnrollmentByStudentAndEnrollmentForClass(studentService.getById(studentId), classService.getById(classId));
  }

}