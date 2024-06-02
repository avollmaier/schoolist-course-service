package at.schoolist.courseservice.web.mapper;

import at.schoolist.courseservice.domain.model.Teacher;
import at.schoolist.courseservice.web.dto.TeacherDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDto, Teacher> {
  @Override
  Teacher toEntity(TeacherDto dto);

  @Override
  TeacherDto toDto(Teacher entity);

  @Override
  List<TeacherDto> toDto(List<Teacher> entityList);
}
