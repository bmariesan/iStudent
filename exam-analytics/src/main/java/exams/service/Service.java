package exams.service;

import exams.StatisticFactory.StatisticFactory;
import exams.domain.*;
import exams.domain.statistics.IStatistic;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

import java.util.ArrayList;
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
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findStudentById(id);
    }

    public Optional<Exam> getExamById(int id){
        return examRepository.findExamById(id);
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

    public List<Exam> getExams(){
        return examRepository.findAll();
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<ExamGrade> getGradesForAnExam(int id){
        List<ExamGrade> grades=new ArrayList<>();
        for(Student s:studentRepository.findAll()){
            grades.addAll(s.gradesForAnExam(id));
        }
        return  grades;
    }

    private void createExams(){
        examRepository.save(Arrays.asList(new Exam(1,"Design Patterns"),
                        new Exam(2,"Formal Languages and Compiler Design"),
                        new Exam(3,"Parallel and Distributed Programming"),
                        new Exam(4,"Mobile Application Programming"),
                        new Exam(5,"Databases"),
                        new Exam(6,"Software Engineering")));
    }

    private void createStudents(){
        List<Exam> exams=examRepository.findAll();

        studentRepository.save(Arrays.asList(new Student(1,"Teodora Berende",21, Gender.FEMALE,"Romania",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),10.00),
                                new ExamGrade(exams.get(1),8.67),
                                new ExamGrade(exams.get(2),9.00),
                                new ExamGrade(exams.get(3),9.50),
                                new ExamGrade(exams.get(4),9.65),
                                new ExamGrade(exams.get(5),8.30))),
                new Student(2,"Aurel Nicolescu",22,Gender.MALE,"Denmark",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),10.00),
                                new ExamGrade(exams.get(1),8.43),
                                new ExamGrade(exams.get(2),6.00),
                                new ExamGrade(exams.get(3),8.50),
                                new ExamGrade(exams.get(4),9.00),
                                new ExamGrade(exams.get(5),5.00))),
                new Student(3,"Andrei Brie", 20, Gender.MALE,"Romania",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),9.00),
                                new ExamGrade(exams.get(1),7.54),
                                new ExamGrade(exams.get(2),7.67),
                                new ExamGrade(exams.get(3),6.78),
                                new ExamGrade(exams.get(4),7.25),
                                new ExamGrade(exams.get(5),8.10))),
                new Student(4,"Carmen Cirebea",21, Gender.FEMALE,"Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),10.00),
                                new ExamGrade(exams.get(1),8.77),
                                new ExamGrade(exams.get(2),8.20),
                                new ExamGrade(exams.get(3),8.45),
                                new ExamGrade(exams.get(4),7.30),
                                new ExamGrade(exams.get(5),9.20))),
                new Student(5,"Catalin Hotea",22,Gender.MALE, "Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),10.00),
                                new ExamGrade(exams.get(1),8.55),
                                new ExamGrade(exams.get(2),5.30),
                                new ExamGrade(exams.get(3),6.00),
                                new ExamGrade(exams.get(4),6.50),
                                new ExamGrade(exams.get(5),8.75))),
                new Student(6, "Andrei Bodea",26, Gender.MALE, "Denmark",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),5.32),
                                new ExamGrade(exams.get(1),5.60),
                                new ExamGrade(exams.get(2),7.55),
                                new ExamGrade(exams.get(3),8.20),
                                new ExamGrade(exams.get(4),10.00),
                                new ExamGrade(exams.get(5),7.70))),
                new Student(7,"Corina Adam",27, Gender.FEMALE,"Romania",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),9.00),
                                new ExamGrade(exams.get(1),8.50),
                                new ExamGrade(exams.get(2),9.30),
                                new ExamGrade(exams.get(3),9.50),
                                new ExamGrade(exams.get(4),9.70),
                                new ExamGrade(exams.get(5),9.40))),
                new Student(8,"Mihai Matyas",25,Gender.MALE,"Denmark",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),9.10),
                                new ExamGrade(exams.get(1),8.20),
                                new ExamGrade(exams.get(2),7.50),
                                new ExamGrade(exams.get(3),8.05),
                                new ExamGrade(exams.get(4),9.50),
                                new ExamGrade(exams.get(5),9.25))),
                new Student(9,"Radu Cora",26,Gender.MALE,"Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),10.00),
                                new ExamGrade(exams.get(1),10.00),
                                new ExamGrade(exams.get(2),10.00),
                                new ExamGrade(exams.get(3),10.00),
                                new ExamGrade(exams.get(4),10.00),
                                new ExamGrade(exams.get(5),10.00))),
                new Student(10,"Daniela Bica", 21,Gender.FEMALE,"Romania",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),7.30),
                                new ExamGrade(exams.get(1),8.50),
                                new ExamGrade(exams.get(2),5.00),
                                new ExamGrade(exams.get(3),6.00),
                                new ExamGrade(exams.get(4),9.40),
                                new ExamGrade(exams.get(5),5.20))),
                new Student(11,"Emilia Irimia",22,Gender.FEMALE,"Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),3.00),
                                new ExamGrade(exams.get(1),2.80),
                                new ExamGrade(exams.get(2),4.30),
                                new ExamGrade(exams.get(3),6.50),
                                new ExamGrade(exams.get(4),5.20),
                                new ExamGrade(exams.get(5),7.10))),
                new Student(12,"Popa Alexandra",22,Gender.FEMALE,"Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),5.00),
                                new ExamGrade(exams.get(1),4.80),
                                new ExamGrade(exams.get(2),3.20),
                                new ExamGrade(exams.get(3),5.00),
                                new ExamGrade(exams.get(4),6.00),
                                new ExamGrade(exams.get(5),5.00))),
                new Student(13,"Maris Irina",22,Gender.FEMALE,"Spain",
                        Arrays.asList(
                                new ExamGrade(exams.get(0),2.50),
                                new ExamGrade(exams.get(1),3.80),
                                new ExamGrade(exams.get(2),6.30),
                                new ExamGrade(exams.get(3),6.15),
                                new ExamGrade(exams.get(4),4.20),
                                new ExamGrade(exams.get(5),5.40)))));

    }
}
