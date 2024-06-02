package at.schoolist.courseservice.web.controller;

import at.schoolist.courseservice.domain.model.Course;
import at.schoolist.courseservice.domain.service.CourseService;
import at.schoolist.courseservice.web.dto.CourseDto;
import at.schoolist.courseservice.web.mapper.CourseMapper;
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
@RequestMapping("course")
@OpenAPIDefinition(info = @Info(title = "Course API", version = "1.0", description = "Documentation Course API"))
public class CourseController {
  private static final Logger log = LoggerFactory.getLogger(CourseController.class);
  private final CourseMapper courseMapper;
  private final CourseService courseService;

  public CourseController(CourseMapper courseMapper, CourseService courseService) {
    this.courseMapper = courseMapper;
    this.courseService = courseService;
  }

  @GetMapping
  public List<CourseDto> get() {
    log.info("Fetching the list of courses");
    List<Course> teachers = courseService.getAll();
    return courseMapper.toDto(teachers);
  }

  @GetMapping("{id}")
  public CourseDto getById(@PathVariable UUID id) {
    log.info("Fetching course with id: {}", id);
    return courseMapper.toDto(courseService.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CourseDto post(@Valid @RequestBody CourseDto courseDto) {
    log.info("Creating a new course with name: {}", courseDto.getCourseName());
    Course createdCourse = courseService.create(courseMapper.toEntity(courseDto));
    return courseMapper.toDto(createdCourse);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    log.info("Deleting course with id: {}", id);
    courseService.delete(id);
  }

  @PutMapping("{id}")
  public CourseDto update(@PathVariable UUID id, @Valid @RequestBody CourseDto courseDto) {
    log.info("Updating course with id: {}", id);
    Course entity = courseMapper.toEntity(courseDto);
    Course updatedCourse = courseService.update(id, entity);
    return courseMapper.toDto(updatedCourse);
  }


}
