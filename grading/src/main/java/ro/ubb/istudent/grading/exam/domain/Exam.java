package ro.ubb.istudent.grading.exam.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public interface Exam extends Serializable {
    ObjectId id();
}

