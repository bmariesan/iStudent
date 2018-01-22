package exams.domain.statistics;
import exams.repository.StudentRepository;
import exams.service.Service;

import java.util.Map;

public class AgeStatistic implements IStatistic {

    private Service service;

    public AgeStatistic(Service service) {
        this.service = service;
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
