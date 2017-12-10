package ro.ubb.istudent.grading.domain;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Marius on 10.12.2017.
 */
public class GradingBook {

    public Date date;
    public Date expiry;
    public Collection<Grade> grades;
    public Collection<Teacher> teacher;

} 
