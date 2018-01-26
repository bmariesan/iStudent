package exams.domain.utils;

import exams.domain.Exam;
import exams.domain.ExamGrade;
import exams.domain.Student;
import exams.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesUtils {

    Service service;
    private Exam theExam;
    private Logger logger = Logger.getLogger("INFO");

    public BayesUtils(Service service, Exam theExam) {
        this.service=service;
        this.theExam=theExam;
    }

    public List<Student> studentsThatHadExam(){
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=new ArrayList<>();
        for(Student s:allStudents){
            List<ExamGrade> allExamGrades=s.getGrades();
            int  gradesForTheExam=0;
            for(ExamGrade eg:allExamGrades){
                if(eg.getExam().getId()==theExam.getId())
                    gradesForTheExam++;
            }
            if(gradesForTheExam>0)
                studentsThatHadExam.add(s);

        }
        //logger.log(Level.INFO,"UTIL studentsThatHavePassed result:"+String.valueOf(studentsThatHadExam.size()));
        return studentsThatHadExam;

    }

    public List<Student> studentsThatHavePassed(List<Student> studentList){
        //logger.log(Level.INFO,"UTIL 1 studentsThatHavePassed studentList:"+String.valueOf(studentList.size()));
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
        //logger.log(Level.INFO,"UTIL studentsThatHavePassed result:"+String.valueOf(studentsThatHavePassed.size()));
        return studentsThatHavePassed;
    }

    public float getProbOfGlobalPassing(){
        //List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().).collect(Collectors.toList());
        List<Student> studentsThatHadExam=studentsThatHadExam();

        int numberOfStudentsThatHadExam=studentsThatHadExam.size();

        //logger.log(Level.INFO,"UTIL getProbOfGlobalPassing 1 studentsThatHadExam"+String.valueOf(numberOfStudentsThatHadExam));

        List<Student> studentsThatHavePassed=studentsThatHavePassed(studentsThatHadExam);
        int numberOfStudentsThatHadPassedExam=studentsThatHavePassed.size();
        //logger.log(Level.INFO,"UTIL getProbOfGlobalPassing 2 studentsPassedExam"+String.valueOf(numberOfStudentsThatHadPassedExam));

        float probOfGlobalPassing=(float)numberOfStudentsThatHadPassedExam/numberOfStudentsThatHadExam;
        //logger.log(Level.INFO,"UTIL getProbOfGlobalPassing 3 studentsPassedExam"+probOfGlobalPassing);
        return probOfGlobalPassing;
    }

}
