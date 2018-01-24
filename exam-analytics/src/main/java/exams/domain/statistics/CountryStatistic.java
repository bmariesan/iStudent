package exams.domain.statistics;

import exams.repository.StudentRepository;
import exams.service.Service;

import java.util.Map;

public class CountryStatistic implements IStatistic {
    private Service service;

    public CountryStatistic(Service service) {
        this.service = service;
    }

    @Override
    public void generateStatistic() {

    }

    @Override
    public Map<String, Float> getData() {
        return null;
    }
}
