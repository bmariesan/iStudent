package exams.domain.utils;

import exams.domain.Exam;
import exams.domain.ExamGrade;
import exams.domain.Student;
import exams.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesUtils {

    Service service;
    private Exam theExam;

    public BayesUtils(Service service, Exam theExam) {
        this.service=service;
        this.theExam=theExam;
    }

    public List<Student> studentsThatHavePassed(List<Student> studentList){
        List<Student> studentsThatHavePassed=new ArrayList<>();
        for(Student s:studentList){
            List<ExamGrade> gradesForThatExam=s.gradesForAnExam(theExam.getId());
            boolean hasPassed=false;
            if(gradesForThatExam.size()!=0){
                for(ExamGrade eg:gradesForThatExam){
                    if(eg.getGrade()>=5)
                        hasPassed=true;
                }
                if(hasPassed)
                    studentsThatHavePassed.add(s);
            }
        }
        return studentsThatHavePassed;
    }

    public float getProbOfGlobalPassing(){
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().contains(theExam)).collect(Collectors.toList());
        int numberOfStudentsThatHadExam=studentsThatHadExam.size();

        List<Student> studentsThatHavePassed=studentsThatHavePassed(studentsThatHadExam);
        int numberOfStudentsThatHadPassedExam=studentsThatHavePassed.size();

        return numberOfStudentsThatHadPassedExam/numberOfStudentsThatHadExam;
    }

}
