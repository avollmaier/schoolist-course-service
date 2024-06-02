package at.schoolist.courseservice.web.mapper;

import at.schoolist.courseservice.domain.model.Class;
import at.schoolist.courseservice.web.dto.ClassDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapper extends EntityMapper<ClassDto, Class> {
  @Override
  Class toEntity(ClassDto dto);

  @Override
  ClassDto toDto(Class entity);

  @Override
  List<ClassDto> toDto(List<Class> entityList);
}
