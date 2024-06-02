package at.schoolist.courseservice.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
  @NotBlank(message = "Subject name is required")
  private String subjectName;

  @NotBlank(message = "Description is required")
  private String description;
}