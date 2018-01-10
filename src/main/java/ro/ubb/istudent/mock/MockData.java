package ro.ubb.istudent.mock;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.config.MongoConfig;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.enums.GenderEnum;
import ro.ubb.istudent.service.CountryService;
import ro.ubb.istudent.service.CourseService;
import ro.ubb.istudent.service.StudentService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class MockData {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MongoConfig mongoConfig;

    @PostConstruct
    public void init() {
        try {
            mongoConfig.mongo().dropDatabase("istudent");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB NOT DROPPED!");
        }

        // create countries / nationalities
        List<CountryDto> countryDtoList = ImmutableList.of(
                CountryDto.builder().countryName("Romania").build(),
                CountryDto.builder().countryName("Germany").build(),
                CountryDto.builder().countryName("England").build()
        );

        // save contries / nationalities
        countryDtoList.forEach(countryService::save);

        // create courses
        List<CourseDto> courseDtoList = ImmutableList.of(
                CourseDto.builder().minimumGrade(5).name("Java").build(),
                CourseDto.builder().minimumGrade(5).name("C#").build(),
                CourseDto.builder().minimumGrade(6).name("Python").build(),
                CourseDto.builder().minimumGrade(6).name("C").build(),
                CourseDto.builder().minimumGrade(7).name("Assembly").build()
        );

        // save courses
        courseDtoList.forEach(courseService::save);

        // get existing countries which we saved before
        CountryDto romania = countryService.findByCountryName("Romania").get();
        CountryDto germany = countryService.findByCountryName("Germany").get();
        CountryDto england = countryService.findByCountryName("England").get();

        // create students
        List<StudentDto> studentDtoList = ImmutableList.of(
                StudentDto.builder().name("Andrei").age(21).gender(GenderEnum.MALE).countryDto(romania).build(),
                StudentDto.builder().name("Mihai").age(27).gender(GenderEnum.MALE).countryDto(romania).build(),
                StudentDto.builder().name("Elena").age(22).gender(GenderEnum.FEMALE).countryDto(romania).build(),
                StudentDto.builder().name("Iliescu").age(100).gender(GenderEnum.MALE).countryDto(romania).build(),
                StudentDto.builder().name("Hans").age(28).gender(GenderEnum.MALE).countryDto(germany).build(),
                StudentDto.builder().name("Aneta").age(34).gender(GenderEnum.FEMALE).countryDto(germany).build(),
                StudentDto.builder().name("Schmitz").age(40).gender(GenderEnum.MALE).countryDto(germany).build(),
                StudentDto.builder().name("Beckham").age(45).gender(GenderEnum.MALE).countryDto(england).build(),
                StudentDto.builder().name("Rooney").age(39).gender(GenderEnum.MALE).countryDto(england).build(),
                StudentDto.builder().name("Abbey").age(23).gender(GenderEnum.FEMALE).countryDto(england).build()
        );

        // save students
        studentDtoList.forEach(studentService::save);

        // get existing courses which we saved before
        List<CourseDto> existingCourses = ImmutableList.of(
                courseService.findByName("Java").get(),
                courseService.findByName("C#").get(),
                courseService.findByName("Python").get(),
                courseService.findByName("C").get(),
                courseService.findByName("Assembly").get()
        );


        // create tests
        List<List<TestDto>> testDtoList = ImmutableList.of(
                // Andrei tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(4).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(7).build()),

                // Mihai tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(5).build()),

                // Elena tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(3).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(6).build()),

                // Iliescu tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(10).build()),

                // Hans tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(1).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(7).build()),

                // Aneta tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(6).build()),

                // Schmitz tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(7).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(7).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(7).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(7).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(7).build()),

                // Beckaham tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(8).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(8).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(8).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(8).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(8).build()),

                // Rooney tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(4).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(4).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(4).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(10).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(10).build()),

                // Abbey tests
                ImmutableList.of(
                        TestDto.builder().courseDto(existingCourses.get(0)).grade(5).build(),
                        TestDto.builder().courseDto(existingCourses.get(1)).grade(6).build(),
                        TestDto.builder().courseDto(existingCourses.get(2)).grade(7).build(),
                        TestDto.builder().courseDto(existingCourses.get(3)).grade(8).build(),
                        TestDto.builder().courseDto(existingCourses.get(4)).grade(9).build())
        );


        // get existing students because we want to add some tests to them
        List<StudentDto> existingStudents = studentService.findAll();


        // now we set the list of tests for each students and we update in database
        IntStream.range(0, studentDtoList.size())
                .boxed()
                .forEach(i ->
                {
                    StudentDto studentDto = existingStudents.get(i);

                    studentDto.setTests(testDtoList.get(i));
                    studentService.save(studentDto);
                });

        studentService.findByCountry(countryService.findByCountryName("Romania").get());
    }
}
