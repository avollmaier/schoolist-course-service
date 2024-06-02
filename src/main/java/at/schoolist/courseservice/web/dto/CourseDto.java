package at.schoolist.courseservice.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
  @NotBlank(message = "Course name is required")
  private String courseName;

  @NotBlank(message = "Description is required")
  private String description;
}