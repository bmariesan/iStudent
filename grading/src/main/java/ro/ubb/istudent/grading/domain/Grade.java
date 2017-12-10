package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Marius on 10.12.2017.
 */
public class Grade {

    @Id
    public String id;
    public Double value;
    public Date date;
    public Teacher teacher;
    public Student student;

}
