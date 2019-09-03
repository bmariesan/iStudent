package exams.domain.statistics;

import exams.domain.Exam;
import exams.domain.ExamGrade;
import exams.service.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * For every exam it makes an average of all grades at that exam
 */
public class ExamStatistic implements IStatistic {
    private Service service;
    List<StringTuple> returnedData;

    public ExamStatistic(Service service){
        this.service=service;
        returnedData = new ArrayList<>();
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
            returnedData.add(new StringTuple(exam.getTitle(), String.valueOf(sum)));
        }
    }

//    @Override
//    public Map<String, Float> getData() {
//        return studentAverageMap;
//    }
    @Override
    public List<StringTuple> getData(){
        return returnedData;
    }
}
