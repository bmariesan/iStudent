package exams.domain;
/**
 * Created by Teodora on 17/01/2018.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@Document(collection = "student")
public class Student implements Serializable {
    @Id
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private String country;
    private List<ExamGrade> grades=new ArrayList<>();

    public Student(){}
    public Student(int id, String name, int age, Gender gender, String country, List<ExamGrade> grades){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.country=country;
        this.grades=grades;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender){
        this.gender=gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ExamGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<ExamGrade> grades) {
        this.grades = grades;
    }

    public void addGrade(Exam exam, double grade){
        ExamGrade examGrade=new ExamGrade(exam,grade);
        if(grades.contains(examGrade)){
            grades.get(grades.indexOf(examGrade)).setGrade(grade);
        }
        else{
            grades.add(examGrade);
        }
    }

    public float SumAllGrades(){
        float sum=0;
        for(ExamGrade grade:grades){
            sum+=grade.getGrade();
        }
        return sum;
    }

    //For a given id of an exam returns all grades of the student at that exam
    public List<ExamGrade> gradesForAnExam(int id){
        List<ExamGrade> gradesExam=new ArrayList<>();
        for(ExamGrade grade: grades){
            if(grade.hasExam(id)){
                gradesExam.add(grade);
            }
        }
        return gradesExam;
    }


    @Override
    public String toString() {
        String s="{\"id\":"+ id +
                ", \"name\":\"" + name +
                "\",\"age:" + age +
                ",\"gender\":\"" + gender +
                "\", \"country\":\"" + country +"\","+
                "\"grades\":[";
        for(ExamGrade e: grades){
            s+=e.toString()+" , ";
        }
        s+="]}";
        return s;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Student student = (Student) other;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name)&&
                Objects.equals(age, student.age) && Objects.equals(gender,student.gender)
                && Objects.equals(country,student.country);

    }

}
