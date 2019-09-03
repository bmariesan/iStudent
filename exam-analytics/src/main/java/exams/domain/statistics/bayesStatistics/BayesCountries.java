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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesCountries implements IStatistic {

    private Service service;
    private Exam theExam;
    List<StringTuple> returnedData;
    BayesUtils bayesUtils;
    private Logger logger = Logger.getLogger("INFO");

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
            logger.log(Level.INFO,"COUNTRY  generateStatistic 1 probOfCountry"+String.valueOf(probOfCountry));
            float probOfPassCountry=getProbPassCountry(country);
            logger.log(Level.INFO,"COUNTRY  generateStatistic 2  probOfPassCountry"+String.valueOf(probOfPassCountry));
            float probability=(probOfPassCountry*passingProb)/probOfCountry;
            logger.log(Level.INFO,"COUNTRY  generateStatistic 3 probability"+String.valueOf(probability));

            returnedData.add(new StringTuple(country, String.valueOf(probability)));
        }


    }



    private float getProbOfCountry(String theCountry){
        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();
        logger.log(Level.INFO,"COUNTRY getProbOfCountry 1 studentsThatHadExam"+String.valueOf(studentsThatHadExam.size()));
        List<String> studentsFromCountry=new ArrayList<>();
        for(Student s:studentsThatHadExam){
            if(s.getCountry().equals(theCountry)){
                studentsFromCountry.add(s.getCountry());
            }
        }
        logger.log(Level.INFO,"COUNTRY getProbOfCountry 2 studentsFromCountry"+String.valueOf(studentsFromCountry.size()));
        float probOfCountry=(float)studentsFromCountry.size()/studentsThatHadExam.size();
        logger.log(Level.INFO,"COUNTRY getProbOfCountry 3 probOfCountry"+String.valueOf(probOfCountry));

        return probOfCountry;

    }

    private float getProbPassCountry(String theCountry){
        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();
        logger.log(Level.INFO,"COUNTRY  getProbPassCountry 2 studentsThatHadExam"+String.valueOf(studentsThatHadExam.size()));
        List<Student> studentsThatHavePassedExam=bayesUtils.studentsThatHavePassed(studentsThatHadExam);

        logger.log(Level.INFO,"COUNTRY  getProbPassCountry 2 studentsThatHavePassedExam"+String.valueOf(studentsThatHavePassedExam.size()));
        List<Student> studentsCountry=new ArrayList<>();

        for(Student s:studentsThatHavePassedExam){
            if(s.getCountry().equals(theCountry))
                studentsCountry.add(s);
        }
        logger.log(Level.INFO,"COUNTRY getProbPassCountry 3 studentsCountry"+String.valueOf(studentsCountry.size()));
        float probPassCountry=(float)studentsCountry.size()/studentsThatHavePassedExam.size();
        logger.log(Level.INFO,"COUNTRY getProbPassCountry 4 probPassCountry"+String.valueOf(probPassCountry));

        return probPassCountry;

    }

    @Override
    public List<StringTuple> getData() {
        return returnedData;
    }


}
