package exams.domain.statistics.bayesStatistics;

import exams.domain.Exam;
import exams.domain.Student;
import exams.domain.statistics.IStatistic;
import exams.domain.statistics.StringTuple;
import exams.domain.utils.BayesUtils;
import exams.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesCountries implements IStatistic {

    private Service service;
    private Exam theExam;
    List<StringTuple> returnedData;
    BayesUtils bayesUtils;

    public BayesCountries(Service service, int examID) {
        this.service = service;
        returnedData = new ArrayList<>();
        Optional<Exam> optionalExam=service.getExamById(examID);
        theExam=optionalExam.get();
        bayesUtils=new BayesUtils(service,theExam);
        generateStatistic();
    }


    @Override
    public void generateStatistic() {
        float passingProb= bayesUtils.getProbOfGlobalPassing();
        List<Student> allStudent=service.getStudents();
        List<String> allcountries=new ArrayList<>();
        for(Student s:allStudent){
            if(!allcountries.contains(s.getCountry()))
                allcountries.add(s.getCountry());
        }
        //getProbOfCountry va fi folosit pentru fiecare tara
        //getProbPassCountry va fi folosit pt fiecare tara iara
        for(String country:allcountries){
            float probOfCountry=getProbOfCountry(country);
            float probOfPassCountry=getProbPassCountry(country);
            float probability=(probOfPassCountry*passingProb)/probOfCountry;
            returnedData.add(new StringTuple(country, String.valueOf(probability)));
        }


    }



    private float getProbOfCountry(String theCountry){
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().contains(theExam)).collect(Collectors.toList());
        List<String> studentsFromCountry=new ArrayList<>();
        for(Student s:studentsThatHadExam){
            if(s.getCountry().equals(theCountry)){
                studentsFromCountry.add(s.getCountry());
            }
        }
        return (studentsThatHadExam.size())/(studentsFromCountry.size());

    }

    private float getProbPassCountry(String theCountry){
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().contains(theExam)).collect(Collectors.toList());
        List<Student> studentsThatHavePassedExam=bayesUtils.studentsThatHavePassed(studentsThatHadExam);

        List<Student> studentsCountry=new ArrayList<>();

        for(Student s:studentsThatHavePassedExam){
            if(s.getCountry().equals(theCountry))
                studentsCountry.add(s);
        }
        return studentsCountry.size()/studentsThatHavePassedExam.size();

    }

    @Override
    public List<StringTuple> getData() {
        return null;
    }


}
