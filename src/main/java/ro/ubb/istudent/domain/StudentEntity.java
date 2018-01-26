package ro.ubb.istudent.domain;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.enums.GenderEnum;

import java.util.ArrayList;
import java.util.List;

//@Data
//@Builder
//@EqualsAndHashCode(callSuper = true) commented by valer
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class StudentEntity extends BaseEntity {

    @Indexed(unique=true)
    private String name;

    @Indexed
    private GenderEnum gender;

    // We know that every year this value needs to be incremented and one day needs to be stopped
    // But as we use this entity as a mock we will assume that age is an Integer for easily manipulation
    private Integer age;

    private List<TestEntity> tests; //= new ArrayList<>(); commented by Valer

    @DBRef
    private CountryEntity country;

    //+Valer ce e sub asta


    public String getName() {
        return name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public List<TestEntity> getTests() {
        return tests;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public static StudentEntityBuilder builder(){
        return  new StudentEntityBuilder();
    }

    public static class StudentEntityBuilder{

        @Indexed(unique=true)
        private String name;

        @Indexed
        private GenderEnum gender;

        private Integer age;

        private List<TestEntity> tests; //= new ArrayList<>(); commented by Valer

        @DBRef
        private CountryEntity country;

        public StudentEntityBuilder name(String name){
            this.name = name;
            return this;
        }

        public StudentEntityBuilder gender(GenderEnum gender){
            this.gender = gender;
            return this;
        }

        public StudentEntityBuilder age(Integer age){
            this.age = age;
            return this;
        }

        public StudentEntityBuilder tests(List<TestEntity> tests){
            this.tests = tests;
            return this;
        }

        public StudentEntityBuilder country(CountryEntity country){
            this.country = country;
            return this;
        }

        public StudentEntity build(){
            return new StudentEntity(this);
        }
    }

    private StudentEntity(StudentEntityBuilder studentEntityBuilder){
        this.name = studentEntityBuilder.name;
        this.gender = studentEntityBuilder.gender;
        this.age = studentEntityBuilder.age;
        this.tests = studentEntityBuilder.tests;
        this.country = studentEntityBuilder.country;
    }
}
