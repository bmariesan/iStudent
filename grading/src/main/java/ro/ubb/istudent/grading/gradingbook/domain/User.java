package ro.ubb.istudent.grading.gradingbook.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;

public interface User extends Serializable {
    ObjectId id();
    String name();
}
