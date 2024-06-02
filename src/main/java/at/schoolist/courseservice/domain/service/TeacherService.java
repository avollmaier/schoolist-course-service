package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.model.Teacher;
import at.schoolist.courseservice.domain.repository.TeacherRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends CrudService<Teacher, UUID> {

  protected TeacherService(TeacherRepository repository) {
    super(repository);
  }

  @Override
  public Teacher update(UUID id, Teacher entity) {
    return super.repository.findById(id)
               .map(existingTeacher -> {
                 var teacherToUpdate = new Teacher(
                     existingTeacher.getId(),
                     entity.getFirstName(),
                     entity.getMiddleName(),
                     entity.getLastName(),
                     entity.getGender(),
                     entity.getEmail(),
                     entity.getPhone(),
                     entity.getPhone(),
                     entity.getCity(),
                     entity.getState(),
                     entity.getZipCode(),
                     entity.getCountry(),
                     entity.getDateOfBirth(),
                     entity.getPhotoURL(),
                     existingTeacher.getClasses(),
                     existingTeacher.getCreatedAt(),
                     existingTeacher.getUpdatedAt(),
                     existingTeacher.getVersion()
                 );
                 return super.repository.save(teacherToUpdate);
               })
               .orElseGet(() -> create(entity));
  }
}
