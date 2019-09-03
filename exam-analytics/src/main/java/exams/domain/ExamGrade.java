package exams.domain;

/**
 * Created by Teodora on 17/01/2018.
 */
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Objects;

/***
 * Contains reference to an exam and the grade for a student that took that exam
 */
@Document(collection = "examGrade")
public class ExamGrade implements Serializable {

    @DBRef
    private Exam exam;
    private double grade;

    public ExamGrade(){}
    public ExamGrade(Exam exam, double grade){
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

    //for a given id, check if this id is the same with the exam's id
    public boolean hasExam(int id){
        if(id==exam.getId()){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "{\"examID\":\""+exam.getId()+"\", \"examTitle\":\""+exam.getTitle()+"\", \"grade\":"+grade+"}";
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ExamGrade examGrade = (ExamGrade) other;
        return Objects.equals(exam, examGrade.exam) && Objects.equals(grade, examGrade.grade);

    }

}
