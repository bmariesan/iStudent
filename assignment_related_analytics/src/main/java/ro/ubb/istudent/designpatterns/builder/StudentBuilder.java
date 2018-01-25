package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.StudentEntity;

/**
 * Created by Cristina on 1/25/2018.
 */

interface StudentBuilder {

    StudentEntity build();

    StudentBuilder firstName(final String firstName);

    StudentBuilder lastName(final String lastName);

    StudentBuilder gender(final String gender);

    StudentBuilder age(final int age);

    StudentBuilder yearOfGraduation(final int yearOfGraduation);

    StudentBuilder countryOfResidence(final String countryOfResidence);

    StudentBuilder gradeBookId(final int gradeBookId);
}
