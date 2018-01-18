package ro.ubb.istudent.grading.gradingbook.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Marius on 10.12.2017.
 */

public interface Grade extends Serializable {
    ObjectId id();
    Double value();
    Calendar created();
    Teacher teacher();
    Student student();
}
