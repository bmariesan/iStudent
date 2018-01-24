package exams.domain.statistics;

import exams.domain.Gender;
import exams.domain.Student;
import exams.service.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * It makes an average of all grades based on gender
 */
public class GenderStatistic implements IStatistic {
    private Service service;
    private Map<String,Float> genderAverageGrades;

    public GenderStatistic(Service service) {
        this.service = service;
        genderAverageGrades=new HashMap<>();
    }

    @Override
    public void generateStatistic() {
        for(Gender gender:Gender.values()) {
            float sum = 0, nr = 0;
            for (Student student : service.getStudentsByGender(gender)) {
                sum += student.SumAllGrades();
                nr += student.getGrades().size();
            }
            sum=sum/nr;
            genderAverageGrades.put(gender.toString(),sum);
        }
    }

    @Override
    public Map<String, Float> getData() {
        return null;
    }
}
