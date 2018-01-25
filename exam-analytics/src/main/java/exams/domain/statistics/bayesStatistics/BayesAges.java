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
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().contains(theExam)).collect(Collectors.toList());
        List<Student> studentsThatHavePassedExam=bayesUtils.studentsThatHavePassed(studentsThatHadExam);

        List<Student> studentsAgeGroup=new ArrayList<>();

        for(Student s:studentsThatHavePassedExam){
            if(s.getAge()>ageGroup.from&&s.getAge()<ageGroup.to)
                studentsAgeGroup.add(s);
        }

        return studentsAgeGroup.size()/studentsThatHavePassedExam.size();

    }

    private float getAge(AgeGroup ageGroup){
        List<Student> allStudents=service.getStudents();
        List<Student> studentsThatHadExam=allStudents.stream().filter(s->s.getGrades().contains(theExam)).collect(Collectors.toList());

        List<Student> studentsAge=new ArrayList<>();

        for(Student s:studentsThatHadExam){
            if(s.getAge()>ageGroup.from&&s.getAge()<ageGroup.to)
                studentsAge.add(s);
        }
        return studentsAge.size()/studentsThatHadExam.size();
    }

    @Override
    public List<StringTuple> getData() {
        return null;
    }
}
