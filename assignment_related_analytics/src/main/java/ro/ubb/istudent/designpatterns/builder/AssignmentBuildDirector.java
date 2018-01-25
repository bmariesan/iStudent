package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.domain.FeedbackEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.domain.TeacherEntity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cristina on 1/25/2018.
 */

public class AssignmentBuildDirector {
    private AssignmentBuilder builder;
    private static long generator = 0;

    public AssignmentBuildDirector(final AssignmentBuilder builder) {
        this.builder = builder;
    }

    public AssignmentEntity construct() {
        generator++;

        StudentBuilder studentBuilder = new StudentBuilderImpl();
        StudentBuildDirector studentBuildDirector = new StudentBuildDirector(studentBuilder);
        StudentEntity studentEntity = studentBuildDirector.construct();

        return builder
                .id(generator)
                .feedback(new FeedbackEntity(generator, new TeacherEntity(),generator ,"Description" ,studentEntity))
                .date(Calendar.getInstance().getTime())
                .deadline(Calendar.getInstance().getTime())
                .description("Math Assign")
                .isCompleted(true)
                .build();
    }
}
