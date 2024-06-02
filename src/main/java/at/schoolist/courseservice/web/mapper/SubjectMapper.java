package at.schoolist.courseservice.web.mapper;

import at.schoolist.courseservice.domain.model.Subject;
import at.schoolist.courseservice.web.dto.SubjectDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDto, Subject> {
  @Override
  Subject toEntity(SubjectDto dto);

  @Override
  SubjectDto toDto(Subject entity);

  @Override
  List<SubjectDto> toDto(List<Subject> entityList);
}
