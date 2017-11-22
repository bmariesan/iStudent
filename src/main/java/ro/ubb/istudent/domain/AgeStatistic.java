package ro.ubb.istudent.domain;

import ro.ubb.istudent.repository.StudentRepository;

public class AgeStatistic implements IStatistic {

    private StudentRepository studentRepository;

    public AgeStatistic(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public IStatistic generateStatistic() {
        return null;
    }
}
