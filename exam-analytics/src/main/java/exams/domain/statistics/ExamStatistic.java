package exams.domain.statistics;


import exams.repository.ExamRepository;
import exams.repository.StudentRepository;
import exams.service.Service;

import java.util.Map;

public class ExamStatistic implements IStatistic {
    private Map<String, Integer> studentAverageMap;
    private Service service;

    public ExamStatistic(Service service){
        this.service=service;
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
