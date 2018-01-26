package exams.domain.statistics.bayesStatistics;

import exams.domain.Exam;
import exams.domain.Gender;
import exams.domain.Student;
import exams.domain.statistics.IStatistic;
import exams.domain.statistics.StringTuple;
import exams.domain.utils.BayesUtils;
import exams.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesGender implements IStatistic {

    private Service service;
    private Exam theExam;
    List<StringTuple> returnedData;
    BayesUtils bayesUtils;
    private Logger logger = Logger.getLogger("INFO");

    public BayesGender(Service service, int examID) {
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
        float maleGenderPassed=getProbGenderPassed(Gender.MALE);
        float femaleGenderPassed=getProbGenderPassed(Gender.FEMALE);
        float maleGender=getProbGender(Gender.MALE);
        float femaleGender=getProbGender(Gender.FEMALE);

        float toReturnMale=maleGenderPassed*passingProb/maleGender;
        float toReturnFemale=femaleGenderPassed*passingProb/femaleGender;

        returnedData.add(new StringTuple(Gender.MALE.toString(), String.valueOf(toReturnMale)));
        returnedData.add(new StringTuple(Gender.FEMALE.toString(), String.valueOf(toReturnFemale)));


        //getProbGender for the 2 gender
        //bayes.utils global passing

    }

    private float getProbGenderPassed(Gender gender){
        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();
        List<Student> studentsThatHavePassedExam=bayesUtils.studentsThatHavePassed(studentsThatHadExam);

        List<Student> studentsGender=new ArrayList<>();
        for(Student s:studentsThatHavePassedExam){
            if(s.getGender().equals(gender))
                studentsGender.add(s);
        }
        float probGenderPassed=(float)studentsGender.size()/studentsThatHavePassedExam.size();
        return probGenderPassed;
    }

    private float getProbGender(Gender gender){
        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();

        List<Student> studentGender=new ArrayList<>();

        for(Student s: studentsThatHadExam){
            if(s.getGender().equals(gender)){
                studentGender.add(s);
            }
        }
        float probGender=(float)studentGender.size()/studentsThatHadExam.size();
        return probGender;
    }



    @Override
    public List<StringTuple> getData() {
        return returnedData;
    }
}
