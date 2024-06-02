package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.exception.ResourceNotFoundException;
import at.schoolist.courseservice.domain.model.Class;
import at.schoolist.courseservice.domain.model.Enrollment;
import at.schoolist.courseservice.domain.model.Student;
import at.schoolist.courseservice.domain.repository.ClassRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ClassService extends CrudService<Class, UUID> {

  protected ClassService(ClassRepository repository) {
    super(repository);
  }

  @Override
  public Class update(UUID id, Class entity) {
    return super.repository.findById(id)
               .map(existingClass -> {
                 var classToUpdate = new Class(
                     existingClass.getId(),
                     entity.getClassName(),
                     existingClass.getCourse(),
                     existingClass.getTeacher(),
                     existingClass.getEnrollments(),
                     existingClass.getCreatedAt(),
                     existingClass.getUpdatedAt(),
                     existingClass.getVersion()
                 );
                 return super.repository.save(classToUpdate);
               })
               .orElseGet(() -> create(entity));
  }

  public List<Student> getStudentsInClass(UUID classId) {
    return super.repository.findById(classId)
               .map(Class::getEnrollments)
               .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + classId))
               .stream()
               .map(Enrollment::getStudent)
               .toList();
  }
}