package ro.ubb.istudent.domain;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by catablack.
 */

@Document(collection = "new")
public class New implements Serializable {
    @Id
    private ObjectId id;

    private String message;

    private String title;

    private String teacher;

    private String course;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        New aNew = (New) o;
        return Objects.equals(id, aNew.id) &&
                Objects.equals(message, aNew.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message);
    }
}
