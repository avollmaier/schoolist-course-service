package at.schoolist.courseservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import java.net.URL;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Student {

  @Id
  @GeneratedValue
  private UUID id;
  private String firstName;
  private String middleName;
  private String lastName;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Temporal(TemporalType.TIMESTAMP)
  private ZonedDateTime dateOfBirth;
  private String email;
  private String phone;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String country;
  private URL photoURL;
  private String emergencyContactName;
  private String emergencyContactPhone;
  @OneToMany(mappedBy = "student")
  private List<Enrollment> enrollments;
  @CreatedDate
  private Instant createdAt;
  @LastModifiedDate
  private Instant updatedAt;
  @Version
  private Long version;
}