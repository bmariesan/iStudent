package ro.ubb.istudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.service.CourseService;
import ro.ubb.istudent.service.StudentService;
import ro.ubb.istudent.service.TestService;

@SpringBootApplication
public class IStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IStudentApplication.class, args);
	}
}
