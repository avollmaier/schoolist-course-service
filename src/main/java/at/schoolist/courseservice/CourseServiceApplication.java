package at.schoolist.courseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class CourseServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CourseServiceApplication.class, args);
  }
}
