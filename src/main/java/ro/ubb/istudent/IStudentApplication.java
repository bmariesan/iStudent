package ro.ubb.istudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ro.ubb.istudent.grading"})
@EnableMongoRepositories(basePackages = {"ro.ubb.istudent.grading"})
public class IStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(IStudentApplication.class, args);
    }
}
