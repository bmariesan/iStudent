package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@Builder Uncomment if bad
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tests")
public class TestEntity extends BaseEntity {
    @DBRef
    private CourseEntity course;

    private Integer grade;

    //+Valer ce e sub asta

    public CourseEntity getCourse() {
        return course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public static TestEntityBuilder builder(){
        return  new TestEntityBuilder();
    }

    public static class TestEntityBuilder{

        @DBRef
        private CourseEntity course;

        private Integer grade;

        public TestEntityBuilder course(CourseEntity course){
            this.course = course;
            return this;
        }

        public TestEntityBuilder grade(Integer grade){
            this.grade = grade;
            return this;
        }

        public TestEntity build(){
            return new TestEntity(this);
        }
    }

    private TestEntity(TestEntityBuilder testEntityBuilder){
        this.course = testEntityBuilder.course;
        this.grade = testEntityBuilder.grade;
    }

}
