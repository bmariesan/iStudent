package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Marius on 10.12.2017.
 */
public class GradingBook {

    @Id
    public String id;
    public Date date;
    public Date expiry;
    public Collection<Grade> grades;
    public Collection<Teacher> teacher;

} 
