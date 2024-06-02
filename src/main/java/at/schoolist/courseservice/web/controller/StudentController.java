package at.schoolist.courseservice.web.controller;

import at.schoolist.courseservice.domain.model.Student;
import at.schoolist.courseservice.domain.service.EnrollmentService;
import at.schoolist.courseservice.domain.service.StudentService;
import at.schoolist.courseservice.web.dto.StudentDto;
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
@RequestMapping("student")
@OpenAPIDefinition(info = @Info(title = "Student API", version = "1.0", description = "Documentation Student API"))
public class StudentController {
  private static final Logger log = LoggerFactory.getLogger(StudentController.class);
  private final StudentService studentService;
  private final StudentMapper studentMapper;
  private final EnrollmentService enrollmentService;

  public StudentController(StudentService studentService, StudentMapper studentMapper, EnrollmentService enrollmentService) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
    this.enrollmentService = enrollmentService;
  }

  @GetMapping
  public List<StudentDto> get() {
    log.info("Fetching the list of students");
    List<Student> teachers = studentService.getAll();
    return studentMapper.toDto(teachers);
  }

  @GetMapping("{id}")
  public StudentDto getById(@PathVariable UUID id) {
    log.info("Fetching student with id: {}", id);
    return studentMapper.toDto(studentService.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StudentDto post(@Valid @RequestBody StudentDto studentDto) {
    log.info("Creating a new student with name: {} {}", studentDto.getFirstName(), studentDto.getLastName());
    Student createdStudent = studentService.create(studentMapper.toEntity(studentDto));
    return studentMapper.toDto(createdStudent);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    log.info("Deleting student with id: {}", id);
    studentService.delete(id);
  }

  @PutMapping("{id}")
  public StudentDto update(@PathVariable UUID id, @Valid @RequestBody StudentDto studentDto) {
    log.info("Updating student with id: {}", id);
    Student student = studentMapper.toEntity(studentDto);
    Student updatedStudent = studentService.update(id, student);
    return studentMapper.toDto(updatedStudent);
  }

  @GetMapping("{classId}/students")
  public List<StudentDto> getStudentsInClass(@PathVariable UUID classId) {
    log.info("Fetching the list of students in class with id: {}", classId);
    List<Student> students = studentService.getStudentsInClass(classId);
    return studentMapper.toDto(students);
  }

}
