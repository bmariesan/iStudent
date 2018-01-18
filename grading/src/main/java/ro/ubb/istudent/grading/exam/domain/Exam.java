package ro.ubb.istudent.grading.exam.domain;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.gradingbook.domain.Teacher;

import java.io.Serializable;
import java.util.List;

public interface Exam extends Serializable {
    ObjectId id();
    Teacher createdBy();
}

