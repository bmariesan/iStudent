package ro.ubb.istudent.util;

import ro.ubb.istudent.designpatterns.builder.*;
import ro.ubb.istudent.domain.*;

import java.util.*;

public class MockBuilder {
    static final MockBuilder shared = new MockBuilder();

    public List<AssignmentEntity> assigns = new ArrayList<>();
    public List<CourseEntity> courses = new ArrayList<>();

    static public MockBuilder sharedInstace() {
        return shared;
    }

    private MockBuilder() {
        generateAssignments(10);
        generateCourses(10);
    }

    public void mockData() {
        System.out.println("Data mocked");
    }

    private void generateAssignments(int size){
        for (int i = 0; i < size; i++) {
            assigns.add(getAssignment());
        }
    }

    private void generateCourses(int size){
        for (int i = 0; i < size; i++) {
            courses.add(getCourse());
        }
    }

    private List<AssignmentEntity> genAssignments(int size){
        List<AssignmentEntity> a = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            a.add(getAssignment());
        }
        return a;
    }

    public CourseEntity getCourse(){
        Random r = new Random();
        return new CourseEntity(1l,"Design Patterns"
                ,0l
                ,0l,new Date(),new Date(),genAssignments(r.nextInt()%8),0);
    }

    public AssignmentEntity getAssignment(){
        Random r = new Random();
        final StudentBuilder studentBuilder = new StudentBuilderImpl();
        final StudentBuildDirector studentBuildDirector = new StudentBuildDirector(studentBuilder);
        StudentEntity studentEntity;
        TeacherEntity teacherEntity;
        AssignmentBuilder builder = new AssignmentBuildImpl();
        Integer generator = r.nextInt() % 100;
        if(r.nextBoolean()){
             studentEntity = studentBuildDirector.construct();
             teacherEntity = null;
        }
        else
        {
            studentEntity = null;
            teacherEntity = new TeacherEntity(1l,"asd","asd",GenderEnum.MALE);
        }
        AssignmentEntity a =  builder
                .id(generator)
                .feedback(new FeedbackEntity(generator, teacherEntity,"Description" ,studentEntity,
                        new AssignmentEntity()))
                .date(Calendar.getInstance().getTime())
                .deadline(Calendar.getInstance().getTime())
                .description("Math Assign")
                .isCompleted(r.nextBoolean())
                .attachments(generateList(r.nextInt() % 10 + 1))
                .build();
        return  a;
    }


    private List<Long> generateList(int size){
        List<Long> l = new ArrayList<>();
        for (int i = 0; i <size ; i++) {
            l.add(0l);
        }
        return l;
    }

}
