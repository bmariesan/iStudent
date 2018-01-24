package exams.domain.statistics;

import exams.domain.Exam;
import exams.domain.ExamGrade;
import exams.service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For every exam it makes an average of all grades at that exam
 */
public class ExamStatistic implements IStatistic {
    private Map<String, Float> studentAverageMap;
    private Service service;

    public ExamStatistic(Service service){
        this.service=service;
        studentAverageMap=new HashMap<>();
        generateStatistic();
    }

    @Override
    public void generateStatistic() {
        for(Exam exam:service.getExams()){
            List<ExamGrade> grades=service.getGradesForAnExam(exam.getId());
            float sum=0;
            for(ExamGrade grade:grades){
                sum+=grade.getGrade();
            }
            sum=sum/grades.size();
            studentAverageMap.put(exam.getTitle(),sum);
        }
    }

    @Override
    public Map<String, Float> getData() {
        return studentAverageMap;
    }
}
