package exams.domain.statistics;

import exams.domain.Student;
import exams.service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For every country of residence it makes an average of all grades of all students
 * that has residence in that country
 */
public class CountryStatistic implements IStatistic {
    private Service service;
    private Map<String,Float> countryAverageGrades;

    public CountryStatistic(Service service) {
        this.service = service;
        countryAverageGrades=new HashMap<>();
    }

    @Override
    public void generateStatistic() {
        List<String> countries=new ArrayList<>();
        List<Student> students=service.getStudents();
        int total=students.size(), i=0;
        while(total>0){
            String country=students.get(i).getCountry();
            if(!countries.contains(country)){
                countries.add(country);
                float sum=0, nr=0;
                for (Student student: service.getStudentsByCountry(country)){
                    sum+=student.SumAllGrades();
                    nr+=student.getGrades().size();
                }
                sum=sum/nr;
                countryAverageGrades.put(country,sum);
                total-=service.getStudentsByCountry(country).size();
            }
            i++;
        }
    }

    @Override
    public Map<String, Float> getData() {
        return countryAverageGrades;
    }
}
