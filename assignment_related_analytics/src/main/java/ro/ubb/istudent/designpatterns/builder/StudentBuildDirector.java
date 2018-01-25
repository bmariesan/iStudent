package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.StudentEntity;

/**
 * Created by Cristina on 1/25/2018.
 */

public class StudentBuildDirector {
    private StudentBuilder builder;

    public StudentBuildDirector(final StudentBuilder builder) {
        this.builder = builder;
    }

    public StudentEntity construct() {
        return builder
                .firstName("John")
                .lastName("Smith")
                .gender("MALE")
                .age(25)
                .yearOfGraduation(2014)
                .countryOfResidence("Bulgaria")
                .gradeBookId(1)
                .build();
    }

}
