package exams.domain.statistics;

import exams.repository.StudentRepository;

import java.util.Map;

public class CountryStatistic implements IStatistic {
    private StudentRepository studentRepository;

    public CountryStatistic(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
