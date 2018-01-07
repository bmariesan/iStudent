package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@Document(collection = "exam")
public class Exam implements Serializable {
    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private final List<Exercise> exercises;

    public Exam(final List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Exam() {
        this(new ArrayList<>());
    }

    public Double getTotalScore() {
        return exercises.stream()
                .mapToDouble(Exercise::getScore).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam that = (Exam) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                '}';
    }
}
