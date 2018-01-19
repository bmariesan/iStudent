package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Optional;

@ToString
@Document(collection = "grade")
@EqualsAndHashCode(of = {"id"})
public class NormalGrade implements Grade {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final Double value;

    @JsonProperty
    private final Calendar date;

    @JsonProperty
    public final User student;

    public NormalGrade() {
        this(ObjectId.get(), 0.0, null);
    }

    public NormalGrade(
            final ObjectId id,
            final Double value,
            final User student) {
        this.id = id;
        this.value = value;
        this.date = Calendar.getInstance();
        this.student = student;
    }

    @Override
    public ObjectId id() {
        return this.id;
    }

    @Override
    public Double value() {
        return this.value;
    }

    @Override
    public Calendar created() {
        return this.date;
    }

    @Override
    public Optional<User> student() {
        return Optional.of(student);
    }
}
