package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.domain.FeedbackEntity;
import ro.ubb.istudent.domain.StudentEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Cristina on 1/25/2018.
 */

public class AssignmentBuildDirector {
    private AssignmentBuilder builder;
    private static Integer generator = 0;
    private static Random r = new Random();

    public AssignmentBuildDirector(final AssignmentBuilder builder) {
        this.builder = builder;
    }

    private List<Long> generateList(int size){
        List<Long> l = new ArrayList<>();
        for (int i = 0; i <size ; i++) {
            l.add(0l);
        }
        return l;
    }

    public AssignmentEntity construct() {
        generator++;

        final StudentBuilder studentBuilder = new StudentBuilderImpl();
        final StudentBuildDirector studentBuildDirector = new StudentBuildDirector(studentBuilder);
        StudentEntity studentEntity = studentBuildDirector.construct();

        return builder
                .id(generator)
                .feedback(new FeedbackEntity(generator, null,"Description" ,studentEntity,
                        new AssignmentEntity()))
                .date(Calendar.getInstance().getTime())
                .deadline(Calendar.getInstance().getTime())
                .description("Math Assign")
                .isCompleted(r.nextBoolean())
                .attachments(generateList(r.nextInt() % 10 + 1))
                .build();
    }
}
