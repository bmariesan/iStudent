package exams.domain.statistics;

import exams.domain.Gender;
import exams.domain.Student;
import exams.service.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * It makes an average of all grades based on gender
 */
public class GenderStatistic implements IStatistic {
    private Service service;
    List<StringTuple> returnedData;
    public GenderStatistic(Service service) {
        this.service = service;
        returnedData = new ArrayList<>();
        generateStatistic();
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
            returnedData.add(new StringTuple(gender.toString(), String.valueOf(sum)));
        }
    }

    @Override
    public List<StringTuple> getData(){
        return returnedData;
    }
}
