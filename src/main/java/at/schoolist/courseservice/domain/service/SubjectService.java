package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.model.Subject;
import at.schoolist.courseservice.domain.repository.SubjectRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SubjectService extends CrudService<Subject, UUID> {

  protected SubjectService(SubjectRepository repository) {
    super(repository);
  }

  @Override
  public Subject update(UUID id, Subject entity) {
    return super.repository.findById(id)
               .map(existingSubject -> {
                 var subjectToUpdate = new Subject(
                     existingSubject.getId(),
                     entity.getSubjectName(),
                     entity.getDescription(),
                     existingSubject.getCourses(),
                     existingSubject.getCreatedAt(),
                     existingSubject.getUpdatedAt(),
                     existingSubject.getVersion()
                 );
                 return super.repository.save(subjectToUpdate);
               })
               .orElseGet(() -> create(entity));
  }
}