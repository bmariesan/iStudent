package exams.domain;

/**
 * Created by Teodora on 17/01/2018.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@Document(collection = "exam")
public class Exam implements Serializable{
    @Id
    private int id;
    private String title;

    public Exam(){}
    public Exam(int id, String title){
        this.id=id;
        this.title=title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Exam exam = (Exam) other;
        return Objects.equals(id, exam.id) && Objects.equals(title, exam.title);
    }

    @Override
    public String toString(){
        return "{\"id\":"+id+",\"title\":\""+title+"\"}";
    }
}
