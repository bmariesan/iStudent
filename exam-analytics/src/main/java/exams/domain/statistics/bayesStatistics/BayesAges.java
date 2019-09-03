package exams.domain.statistics.bayesStatistics;

import exams.domain.AgeGroup;
import exams.domain.Exam;
import exams.domain.Student;
import exams.domain.statistics.IStatistic;
import exams.domain.statistics.StringTuple;
import exams.domain.utils.BayesUtils;
import exams.service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Exerdath on 25-Jan-18.
 */
public class BayesAges implements IStatistic {

    private Service service;
    private Exam theExam;
    List<StringTuple> returnedData;
    BayesUtils bayesUtils;
    List<AgeGroup> ageGroups;
    private Logger logger = Logger.getLogger("INFO");


    public BayesAges(Service service, int examID) {
        this.service = service;
        returnedData = new ArrayList<>();
        Optional<Exam> optionalExam=service.getExamById(examID);
        theExam=optionalExam.get();
        bayesUtils=new BayesUtils(service,theExam);
        ageGroups=new ArrayList<>();
        ageGroups.addAll(Arrays.asList(new AgeGroup(10,19),new AgeGroup(18,22), new AgeGroup(21,25),new AgeGroup(24,28),
                new AgeGroup(27,32), new AgeGroup(31,80)));

        generateStatistic();
    }




    @Override
    public void generateStatistic() {
        float passingProb= bayesUtils.getProbOfGlobalPassing();
        //getProbAgePassed age daca treci
        //getAge esti acea varsta din toti

        for(AgeGroup ag:ageGroups){
            float probAgePassed=getProbAgePassed(ag);
            float probAge=getAge(ag);
            float probability=probAgePassed*passingProb/probAge;
            returnedData.add(new StringTuple(ag.toString(), String.valueOf(probability)));
        }

    }





    private float getProbAgePassed(AgeGroup ageGroup){
        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();
        logger.log(Level.INFO,"AGE 1 getProbAgePassed studentsHadExam:"+String.valueOf(studentsThatHadExam.size()));
        List<Student> studentsThatHavePassedExam=bayesUtils.studentsThatHavePassed(studentsThatHadExam);
        logger.log(Level.INFO,"AGE 2 getProbAgePassed studentsPassedExam"+String.valueOf(studentsThatHavePassedExam.size()));

        List<Student> studentsAgeGroup=new ArrayList<>();

        for(Student s:studentsThatHavePassedExam){
            if(s.getAge()>ageGroup.from&&s.getAge()<ageGroup.to)
                studentsAgeGroup.add(s);
        }
        logger.log(Level.INFO,"AGE 3 getProbAgePassed studentsAgeGroup:"+String.valueOf(studentsAgeGroup.size()));
        float probAgePassed=(float)studentsAgeGroup.size()/studentsThatHavePassedExam.size();
        return probAgePassed;

    }

    private float getAge(AgeGroup ageGroup){

        List<Student> studentsThatHadExam=bayesUtils.studentsThatHadExam();

        List<Student> studentsAge=new ArrayList<>();

        for(Student s:studentsThatHadExam){
            if(s.getAge()>ageGroup.from&&s.getAge()<ageGroup.to)
                studentsAge.add(s);
        }
        logger.log(Level.INFO,"AGE 4 getAge studentsAge:"+String.valueOf(studentsAge.size()));
        float getAgeRes=(float)studentsAge.size()/studentsThatHadExam.size();
        return getAgeRes;
    }

    @Override
    public List<StringTuple> getData() {
        return returnedData;
    }
}
