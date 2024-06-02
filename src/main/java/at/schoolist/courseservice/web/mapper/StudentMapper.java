package at.schoolist.courseservice.web.mapper;

import at.schoolist.courseservice.domain.model.Student;
import at.schoolist.courseservice.web.dto.StudentDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDto, Student> {
  @Override
  Student toEntity(StudentDto dto);

  @Override
  StudentDto toDto(Student entity);

  @Override
  List<StudentDto> toDto(List<Student> entityList);
}
