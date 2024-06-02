package at.schoolist.courseservice.web.controller;

import at.schoolist.courseservice.domain.model.Subject;
import at.schoolist.courseservice.domain.service.SubjectService;
import at.schoolist.courseservice.web.dto.SubjectDto;
import at.schoolist.courseservice.web.mapper.SubjectMapper;
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
@RequestMapping("subject")
@OpenAPIDefinition(info = @Info(title = "Subject API", version = "1.0", description = "Documentation Subject API"))
public class SubjectController {
  private static final Logger log = LoggerFactory.getLogger(SubjectController.class);
  private final SubjectService subjectService;
  private final SubjectMapper subjectMapper;


  public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
    this.subjectService = subjectService;
    this.subjectMapper = subjectMapper;
  }

  @GetMapping
  public List<SubjectDto> get() {
    log.info("Fetching the list of subjects");
    List<Subject> subjects = subjectService.getAll();
    return subjectMapper.toDto(subjects);
  }

  @GetMapping("{id}")
  public SubjectDto getById(@PathVariable UUID id) {
    log.info("Fetching subject with id: {}", id);
    return subjectMapper.toDto(subjectService.getById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SubjectDto post(@Valid @RequestBody SubjectDto subjectDto) {
    log.info("Creating a new subject with name: {}", subjectDto.getSubjectName());
    Subject createdSubject = subjectService.create(subjectMapper.toEntity(subjectDto));
    return subjectMapper.toDto(createdSubject);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    log.info("Deleting subject with id: {}", id);
    subjectService.delete(id);
  }

  @PutMapping("{id}")
  public SubjectDto update(@PathVariable UUID id, @Valid @RequestBody SubjectDto subjectDto) {
    log.info("Updating subject with id: {}", id);
    Subject subject = subjectMapper.toEntity(subjectDto);
    Subject updatedSubject = subjectService.update(id, subject);
    return subjectMapper.toDto(updatedSubject);
  }

}
