package at.schoolist.courseservice.web.controller;

import at.schoolist.courseservice.domain.model.Class;
import at.schoolist.courseservice.domain.model.Enrollment;
import at.schoolist.courseservice.domain.model.Student;
import at.schoolist.courseservice.domain.service.ClassService;
import at.schoolist.courseservice.domain.service.EnrollmentService;
import at.schoolist.courseservice.domain.service.StudentService;
import at.schoolist.courseservice.web.dto.ClassDto;
import at.schoolist.courseservice.web.dto.StudentDto;
import at.schoolist.courseservice.web.mapper.ClassMapper;
import at.schoolist.courseservice.web.mapper.StudentMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("class")
@OpenAPIDefinition(info = @Info(title = "Class API", version = "1.0", description = "Documentation Class API"))
public class ClassController {
  private static final Logger log = LoggerFactory.getLogger(ClassController.class);
  private final StudentService studentService;
  private final StudentMapper studentMapper;
  private final EnrollmentService enrollmentService;
  private final ClassMapper classMapper;
  private final ClassService classService;

  public ClassController(StudentService studentService, StudentMapper studentMapper, EnrollmentService enrollmentService, ClassMapper classMapper,
                         ClassService classService) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
    this.enrollmentService = enrollmentService;
    this.classMapper = classMapper;
    this.classService = classService;
  }

  @GetMapping
  public List<ClassDto> get() {
    log.info("Fetching the list of classes");
    List<Class> teachers = classService.getAll();
    return classMapper.toDto(teachers);
  }

  @GetMapping("{id}")
  public ClassDto getById(@PathVariable UUID id) {
    log.info("Fetching class with id: {}", id);
    return classMapper.toDto(classService.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClassDto post(@Valid @RequestBody ClassDto classDto) {
    log.info("Creating a new class with name: {}", classDto.getClassName());
    Class createdClass = classService.create(classMapper.toEntity(classDto));
    return classMapper.toDto(createdClass);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    log.info("Deleting class with id: {}", id);
    classService.delete(id);
  }

  @PutMapping("{id}")
  public ClassDto update(@PathVariable UUID id, @Valid @RequestBody ClassDto classDto) {
    log.info("Updating class with id: {}", id);
    Class entity = classMapper.toEntity(classDto);
    Class updatedClass = classService.update(id, entity);
    return classMapper.toDto(updatedClass);
  }

  @PostMapping("{classId}/student/{studentId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addStudentToClass(@PathVariable UUID classId, @PathVariable UUID studentId) {
    log.info("Adding student with id: {} to class with id: {}", studentId, classId);
    Student student = studentService.getById(studentId);
    Class classEntity = classService.getById(classId);
    Enrollment enrollment = new Enrollment(student, classEntity);
    enrollmentService.create(enrollment);
  }

  @DeleteMapping("{classId}/student/{studentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeStudentFromClass(@PathVariable UUID classId, @PathVariable UUID studentId) {
    log.info("Removing student with id: {} from class with id: {}", studentId, classId);
    enrollmentService.deleteEnrollmentByStudentIdAndClassId(studentId, classId);
  }

  @GetMapping("{classId}/students")
  public List<StudentDto> getStudentsInClass(@PathVariable UUID classId) {
    log.info("Fetching the list of students in class with id: {}", classId);
    List<Student> students = classService.getStudentsInClass(classId);
    return studentMapper.toDto(students);
  }


}
