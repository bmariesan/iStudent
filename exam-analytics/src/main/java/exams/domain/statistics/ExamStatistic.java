package exams.domain.statistics;


import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

import java.util.Map;

public class ExamStatistic implements IStatistic {
    private Map<String, Integer> studentAverageMap;
    private final ExamRepository examRepository;

    public ExamStatistic(ExamRepository examRepository){
        this.examRepository=examRepository;
    }

    @Override
    public IStatistic generateStatistic() {
        


        return null;
    }

    @Override
    public Map<String, Float> getData() {
        return null;
    }
}
