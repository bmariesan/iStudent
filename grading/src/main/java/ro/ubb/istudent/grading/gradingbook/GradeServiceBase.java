package ro.ubb.istudent.grading.gradingbook;

import ro.ubb.istudent.grading.domain.Grade;

import java.util.Enumeration;

/**
 * Created by Marius on 10.12.2017.
 */
public abstract class GradeServiceBase {

    public abstract Iterable<Grade> getGrades();

}

