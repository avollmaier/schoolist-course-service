// Assuming you have a StudentService class, the update method would look like this:

package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.exception.ResourceNotFoundException;
import at.schoolist.courseservice.domain.model.Enrollment;
import at.schoolist.courseservice.domain.model.Student;
import at.schoolist.courseservice.domain.repository.StudentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends CrudService<Student, UUID> {

  protected StudentService(StudentRepository repository) {
    super(repository);
  }

  @Override
  public Student update(UUID id, Student entity) {
    return super.repository.findById(id)
               .map(existingStudent -> {
                 var studentToUpdate = new Student(
                     existingStudent.getId(),
                     entity.getFirstName(),
                     entity.getMiddleName(),
                     entity.getLastName(),
                     entity.getGender(),
                     entity.getDateOfBirth(),
                     entity.getEmail(),
                     entity.getPhone(),
                     entity.getAddress(),
                     entity.getCity(),
                     entity.getState(),
                     entity.getZipCode(),
                     entity.getCountry(),
                     entity.getPhotoURL(),
                     entity.getEmergencyContactName(),
                     entity.getEmergencyContactName(),
                     existingStudent.getEnrollments(),
                     existingStudent.getCreatedAt(),
                     existingStudent.getUpdatedAt(),
                     existingStudent.getVersion()
                 );
                 return super.repository.save(studentToUpdate);
               })
               .orElseGet(() -> create(entity));
  }

  public List<Student> getStudentsInClass(UUID classId) {
    return super.repository.findById(classId)
               .map(Student::getEnrollments)
               .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + classId))
               .stream()
               .map(Enrollment::getStudent)
               .toList();
  }
}