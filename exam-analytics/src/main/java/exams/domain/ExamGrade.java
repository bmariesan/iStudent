package exams.domain;

/**
 * Created by Teodora on 17/01/2018.
 */
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Document(collection = "examGrade")
public class ExamGrade implements Serializable {
    @Id
    private ObjectId id;
    @DBRef
    private Exam exam;
    private double grade;

    public ExamGrade(){}
    public ExamGrade(Exam exam, double grade){
        id=new ObjectId();
        this.exam=exam;
        this.grade=grade;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString(){
        return exam.getId()+" "+exam.getTitle()+" "+grade;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ExamGrade examGrade = (ExamGrade) other;
        return Objects.equals(exam, examGrade.exam) && Objects.equals(grade, examGrade.grade);

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
