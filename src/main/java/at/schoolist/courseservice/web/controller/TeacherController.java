package at.schoolist.courseservice.web.controller;

import at.schoolist.courseservice.domain.model.Teacher;
import at.schoolist.courseservice.domain.service.TeacherService;
import at.schoolist.courseservice.web.dto.TeacherDto;
import at.schoolist.courseservice.web.mapper.TeacherMapper;
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
@RequestMapping("teacher")
@OpenAPIDefinition(info = @Info(title = "Teacher API", version = "1.0", description = "Documentation Teacher API"))
public class TeacherController {
  private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
  private final TeacherService teacherService;
  private final TeacherMapper teacherMapper;


  public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper) {
    this.teacherService = teacherService;
    this.teacherMapper = teacherMapper;
  }

  @GetMapping
  public List<TeacherDto> get() {
    log.info("Fetching the list of teachers");
    List<Teacher> teachers = teacherService.getAll();
    return teacherMapper.toDto(teachers);
  }

  @GetMapping("{id}")
  public TeacherDto getById(@PathVariable UUID id) {
    log.info("Fetching teacher with id: {}", id);
    return teacherMapper.toDto(teacherService.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TeacherDto post(@Valid @RequestBody TeacherDto teacherDto) {
    log.info("Creating a new teacher with name: {} {}", teacherDto.getFirstName(), teacherDto.getLastName());
    Teacher createdTeacher = teacherService.create(teacherMapper.toEntity(teacherDto));
    return teacherMapper.toDto(createdTeacher);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    log.info("Deleting teacher with id: {}", id);
    teacherService.delete(id);
  }

  @PutMapping("{id}")
  public TeacherDto update(@PathVariable UUID id, @Valid @RequestBody TeacherDto teacherDto) {
    log.info("Updating teacher with id: {}", id);
    Teacher teacher = teacherMapper.toEntity(teacherDto);
    Teacher updatedTeacher = teacherService.update(id, teacher);
    return teacherMapper.toDto(updatedTeacher);
  }

}
