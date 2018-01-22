package exams.service;

import exams.domain.*;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Teodora on 18/01/2018.
 */
@org.springframework.stereotype.Service
public class Service {

    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    public Service(ExamRepository repository, StudentRepository stdRepo) {
        this.examRepository = repository;
        studentRepository=stdRepo;
        createExams();
        createStudents();
        createGradesForStudents();

        List<Exam> exams=examRepository.findAll();
        List<Student> students=studentRepository.findAll();
        for(Exam e:exams){
            System.out.println(e);
        }
        System.out.println();
        for(Student s:students){
            System.out.println(s);
        }

    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findStudentById(id);
    }

    public Optional<Exam> getExamById(int id){
        return examRepository.findExamById(id);
    }

    public List<Student> getStudentsByAge(int age){
        return studentRepository.findAllByAge(age);
    }

    public List<Student> getStudentsBetweenAge(int from, int to){
        return studentRepository.findByAgeBetween(from,to);
    }

    public List<Student> getStudentsByGender(Gender gender){
        return studentRepository.findAllByGender(gender);
    }

    public List<Student> getStudentsByCountry(String country){
        return studentRepository.findAllByCountry(country);
    }




    private void createExams(){
        examRepository.save(Arrays.asList(new Exam(1,"Design Patterns"),
                        new Exam(2,"Formal Languages and Compiler Design"),
                        new Exam(3,"Parallel and Distributed Programming"),
                        new Exam(4,"Mobile Application Programming")));
    }

    private void createStudents(){
        List<Exam> exams=examRepository.findAll();

        studentRepository.save(Arrays.asList(new Student(1,"Teodora Berende",21, Gender.FEMALE,"Romania",
                        Arrays.asList(new ExamGrade(exams.get(0),10.00), new ExamGrade(exams.get(2),8.67))),
                new Student(2,"Aurel Nicolescu",22,Gender.MALE,"Denmark",
                        Arrays.asList(new ExamGrade(exams.get(0),10.00), new ExamGrade(exams.get(1),8.43))),
                new Student(3,"Andrei Brie", 20, Gender.MALE,"Romania",
                        Arrays.asList(new ExamGrade(exams.get(1),7.54), new ExamGrade(exams.get(2),7.67),
                                new ExamGrade(exams.get(3),6.78))),
                new Student(4,"Carmen Cirebea",21, Gender.FEMALE,"Spain",
                        Arrays.asList(new ExamGrade(exams.get(0),10.00), new ExamGrade(exams.get(3),8.77))),
                new Student(5,"Catalin Hotea",22,Gender.MALE, "Spain",
                        Arrays.asList(new ExamGrade(exams.get(0),10.00), new ExamGrade(exams.get(2),8.67),
                                new ExamGrade(exams.get(1), 6.34))),
                new Student(6, "Andrei Bodea",26, Gender.MALE, "Denmark",
                        Arrays.asList(new ExamGrade(exams.get(1),5.32), new ExamGrade(exams.get(2),6.60))),
                new Student(7,"Corina Adam",27, Gender.FEMALE,"Romania",
                        Arrays.asList(new ExamGrade(exams.get(3),9.00), new ExamGrade(exams.get(2),8.00))),
                new Student(8,"Mihai Matyas",25,Gender.MALE,"Denmark",
                        Arrays.asList(new ExamGrade(exams.get(0),10.00), new ExamGrade(exams.get(1),8.23))),
                new Student(9,"Radu Cora",26,Gender.MALE,"Spain",
                        Arrays.asList(new ExamGrade(exams.get(2),7.60), new ExamGrade(exams.get(3),5.37))),
                new Student(10,"Daniela Bica", 21,Gender.FEMALE,"Romania",
                        Arrays.asList(new ExamGrade(exams.get(1),7.30), new ExamGrade(exams.get(3),8.50))),
                new Student(11,"Emilia Irimia",22,Gender.FEMALE,"Spain",
                        Arrays.asList(new ExamGrade(exams.get(0),9.00), new ExamGrade(exams.get(2),8.80)))));

    }

    private void createGradesForStudents(){
        List<Student> students=studentRepository.findAll();
        Optional<Exam> exam1=examRepository.findExamById(1);
        exam1.get().setGrades(Arrays.asList(new StudentGrade(students.get(0),10.00),
                new StudentGrade(students.get(1),10.00), new StudentGrade(students.get(3),10.00),
                new StudentGrade(students.get(4),10.00), new StudentGrade(students.get(7),10.00),
                new StudentGrade(students.get(10),9.00)));

        Optional<Exam> exam2=examRepository.findExamById(2);
        exam2.get().setGrades(Arrays.asList(new StudentGrade(students.get(1),8.43),
                new StudentGrade(students.get(2),7.54), new StudentGrade(students.get(4),6.34),
                new StudentGrade(students.get(5),5.32), new StudentGrade(students.get(7),8.23),
                new StudentGrade(students.get(9),7.30)));

        Optional<Exam> exam3=examRepository.findExamById(3);
        exam3.get().setGrades(Arrays.asList(new StudentGrade(students.get(0),8.67),
                new StudentGrade(students.get(2),7.67), new StudentGrade(students.get(4),8.67),
                new StudentGrade(students.get(5),6.60), new StudentGrade(students.get(6),8.00),
                new StudentGrade(students.get(8),7.60), new StudentGrade(students.get(10),8.80)));

        Optional<Exam> exam4=examRepository.findExamById(4);
        exam4.get().setGrades(Arrays.asList(new StudentGrade(students.get(2),6.78),
                new StudentGrade(students.get(3),8.77), new StudentGrade(students.get(6),9.00),
                new StudentGrade(students.get(8),5.37),new StudentGrade(students.get(9),8.50)));

        examRepository.save(exam1.get());
        examRepository.save(exam2.get());
        examRepository.save(exam3.get());
        examRepository.save(exam4.get());
    }
}
