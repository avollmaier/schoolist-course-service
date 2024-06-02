package at.schoolist.courseservice.web.dto;

import at.schoolist.courseservice.domain.model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
  @NotBlank(message = "First name is required")
  private String firstName;

  private String middleName;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @NotNull(message = "Gender is required")
  private Gender gender;

  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Phone number is required")
  private String phone;

  @NotBlank(message = "Address is required")
  private String address;

  @NotBlank(message = "City is required")
  private String city;

  @NotBlank(message = "State is required")
  private String state;

  @NotBlank(message = "Zip code is required")
  private String zipCode;

  @NotBlank(message = "Country is required")
  private String country;

  @NotNull(message = "Date of birth is required")
  private ZonedDateTime dateOfBirth;

  private URL photoURL;
}