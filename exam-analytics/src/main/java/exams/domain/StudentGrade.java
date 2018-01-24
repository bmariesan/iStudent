package exams.domain;
/**
 * Created by Teodora on 17/01/2018.
 */
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "examGrade")
public class StudentGrade implements Serializable {
    @Id
    private ObjectId id;
    @DBRef
    private Student student;
    private double grade;

    public StudentGrade(){}

    public StudentGrade(Student student, double grade){
        this.id = new ObjectId();
        this.student=student;
        this.grade=grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString(){
        return student.getId()+" "+student.getName()+" "+grade;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        StudentGrade studentGrade = (StudentGrade) other;
        return Objects.equals(student, studentGrade.student) && Objects.equals(grade, studentGrade.grade);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
