package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/*@Data
@Builder
@EqualsAndHashCode(callSuper = true) commented by valer*/
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class CourseEntity extends BaseEntity {

    @Indexed(unique=true)
    private String name;

    private Integer minimumGrade;

    @DBRef
    private List<TestEntity> tests;// = new ArrayList<>(); commented by Valer

    //+Valer ce e sub asta


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(Integer minimumGrade) {
        this.minimumGrade = minimumGrade;
    }

    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }

    public static CourseEntityBuilder builder(){
        return  new CourseEntityBuilder();
    }

    public static class CourseEntityBuilder{

        @Indexed(unique=true)
        private String name;

        private Integer minimumGrade;

        @DBRef
        private List<TestEntity> tests; //= new ArrayList<>(); commented by Valer

        @DBRef
        private CountryEntity country;

        public CourseEntityBuilder name(String name){
            this.name = name;
            return this;
        }

        public CourseEntityBuilder minimumGrade(Integer minimumGrade){
            this.minimumGrade = minimumGrade;
            return this;
        }

        public CourseEntityBuilder tests(List<TestEntity> tests){
            this.tests = tests;
            return this;
        }

        public CourseEntity build(){
            return new CourseEntity(this);
        }
    }

    private CourseEntity(CourseEntityBuilder courseEntityBuilder){
        this.name = courseEntityBuilder.name;
        this.minimumGrade = courseEntityBuilder.minimumGrade;
        this.tests = courseEntityBuilder.tests;
    }
}
