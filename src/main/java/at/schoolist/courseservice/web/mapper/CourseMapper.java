package at.schoolist.courseservice.web.mapper;

import at.schoolist.courseservice.domain.model.Course;
import at.schoolist.courseservice.web.dto.CourseDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDto, Course> {
  @Override
  Course toEntity(CourseDto dto);

  @Override
  CourseDto toDto(Course entity);

  @Override
  List<CourseDto> toDto(List<Course> entityList);
}
