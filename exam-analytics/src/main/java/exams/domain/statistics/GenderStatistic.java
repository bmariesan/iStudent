package exams.domain.statistics;

import exams.service.Service;

import java.util.Map;

public class GenderStatistic implements IStatistic {
    private Service service;

    public GenderStatistic(Service service) {
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
