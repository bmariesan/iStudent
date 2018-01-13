package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@Immutable
@Document(collection = "exam")
@ToString(of = {"id", "exercises"})
@EqualsAndHashCode(of = {"id"})
public class Exam implements Serializable {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final List<Exercise> exercises;

    public Exam() {
        this(ObjectId.get(), new ArrayList<>());
    }

    public Exam(final ObjectId id, final List<Exercise> exercises) {
        this.id = id;
        this.exercises = exercises;
    }

    public ObjectId getId() {
        return id;
    }

    public Double getTotalScore() {
        return exercises.stream()
                .mapToDouble(Exercise::getScore).sum();
    }
}
