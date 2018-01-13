package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.concurrent.Immutable;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import ro.ubb.istudent.grading.course.Course;

import java.io.Serializable;
import java.util.*;


@Immutable
@EqualsAndHashCode
@ToString
@Document(collection = "gradingbook")
public class GradingBook implements Serializable {

    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final Course course;

    @JsonProperty
    private final Calendar createdDate;

    @JsonProperty
    private final List<Grade> grades;

    @JsonProperty
    private final List<Teacher> teachers;

    public GradingBook() {
        this(ObjectId.get(), null, null, null);
    }

    public GradingBook(ObjectId id) {
        this(id, null, null, null);
    }

    public GradingBook(
            final ObjectId id,
            final Course course,
            final List<Grade> grades,
            final List<Teacher> teachers) {
        this.id = id;
        this.course = course;
        this.createdDate = Calendar.getInstance();
        this.grades = grades;
        this.teachers = teachers;
    }

    public ObjectId id() {
        return id;
    }
    public Course course(){
        return course;
    }
    public Calendar calendar(){
        return createdDate;
    }
    public List<Grade> getGrades(){
        return grades;
    }
    public List<Teacher> getTeachers(){
        return teachers;
    }

}