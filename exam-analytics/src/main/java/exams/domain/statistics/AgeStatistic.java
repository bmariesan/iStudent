package exams.domain.statistics;
import exams.domain.Student;
import exams.service.Service;

import java.util.*;

class AgeGroup {
    public int from;
    public int to;
    public AgeGroup(int from, int to){
        this.from=from;
        this.to=to;
    }
    @Override
    public String toString(){
        return from+" - "+to;
    }
}

/**
 * For every age group it makes an average of all grades of the students that are part of the age group
 */
public class AgeStatistic implements IStatistic {

    private Service service;
    private Map<String, Float> ageAverageGrades;
    private List<AgeGroup> ageGroups;

    public AgeStatistic(Service service) {
        this.service = service;
        ageAverageGrades=new HashMap<>();
        ageGroups=new ArrayList<>();
        ageGroups.addAll(Arrays.asList(new AgeGroup(10,19),new AgeGroup(18,22), new AgeGroup(21,25),new AgeGroup(24,28),
                new AgeGroup(27,32), new AgeGroup(31,80)));
        generateStatistic();
    }


    @Override
    public void generateStatistic() {
        for(AgeGroup group:ageGroups){
            float sum=0,nr=0;
            for(Student student: service.getStudentsBetweenAge(group.from,group.to)){
                sum+=student.SumAllGrades();
                nr+=student.getGrades().size();
            }
            sum=sum/nr;
            ageAverageGrades.put(group.toString(),sum);
        }
    }

    @Override
    public Map<String, Float> getData() {
        return ageAverageGrades;
    }
}
