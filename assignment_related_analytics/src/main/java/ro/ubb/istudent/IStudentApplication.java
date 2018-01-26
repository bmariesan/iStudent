package ro.ubb.istudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.ubb.istudent.util.MockBuilder;

@SpringBootApplication
public class IStudentApplication {

	public static void main(String[] args) {
		MockBuilder.sharedInstace();
		SpringApplication.run(IStudentApplication.class, args);
	}
}
