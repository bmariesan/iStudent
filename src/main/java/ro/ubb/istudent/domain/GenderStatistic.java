package ro.ubb.istudent.domain;

import ro.ubb.istudent.repository.StudentRepository;

public class GenderStatistic implements IStatistic {
    private StudentRepository studentRepository;

    public GenderStatistic(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public IStatistic generateStatistic() {
        return null;
    }
}
