package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.model.Course;
import at.schoolist.courseservice.domain.repository.CourseRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends CrudService<Course, UUID> {

  protected CourseService(CourseRepository repository) {
    super(repository);
  }

  @Override
  public Course update(UUID id, Course entity) {
    return super.repository.findById(id)
               .map(existingCourse -> {
                 var courseToUpdate = new Course(
                     existingCourse.getId(),
                     entity.getCourseName(),
                     entity.getDescription(),
                     existingCourse.getSubjects(),
                     existingCourse.getCreatedAt(),
                     existingCourse.getUpdatedAt(),
                     existingCourse.getVersion()
                 );
                 return super.repository.save(courseToUpdate);
               })
               .orElseGet(() -> create(entity));
  }
}