package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.StudentEntity;

/**
 * Created by Cristina on 1/25/2018.
 */

class StudentBuilderImpl implements StudentBuilder {
    private StudentEntity student;

    public StudentBuilderImpl() {
        student = new StudentEntity();
    }

    @Override
    public StudentEntity build() {
        return student;
    }

    @Override
    public StudentBuilder firstName(final String firstName) {
        student.setFirstName(firstName);
        return this;
    }

    @Override
    public StudentBuilder lastName(final String lastName) {
        student.setLastName(lastName);
        return this;
    }

    @Override
    public StudentBuilder gender(final String gender) {
        student.setGender(gender);
        return this;
    }

    @Override
    public StudentBuilder age(final int age) {
        student.setAge(age);
        return this;
    }

    @Override
    public StudentBuilder yearOfGraduation(final int yearOfGraduation) {
        student.setYearOfGraduation(yearOfGraduation);
        return this;
    }

    @Override
    public StudentBuilder countryOfResidence(final String countryOfResidence) {
        student.setCountryOfResidence(countryOfResidence);
        return this;
    }

    @Override
    public StudentBuilder gradeBookId(final int gradeBookId) {
        student.setGradeBookId(gradeBookId);
        return this;
    }
}