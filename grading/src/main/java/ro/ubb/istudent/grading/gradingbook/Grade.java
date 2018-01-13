package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;




import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.annotation.concurrent.Immutable;
import java.util.Calendar;


/**
 * Created by Marius on 10.12.2017.
 */

@ToString
@Immutable
@EqualsAndHashCode
@Document(collection = "grade")
public class Grade {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final Double value;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm")
    private final Calendar date;

    @JsonProperty
    private final Teacher teacher;

    @JsonProperty
    private final Student student;

    public Grade() {
        this(ObjectId.get(), 0.0,
                Calendar.getInstance(), null, null);
    }

    public Grade(ObjectId id, final Double value) {
        this(id, value, Calendar.getInstance(), null, null);
    }

    public Grade(
            final ObjectId id,
            final Double value,
            final Calendar date,
            final Teacher teacher,
            final Student student) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.teacher = teacher;
        this.student = student;
    }

    public ObjectId id() {
        return id;
    }

    public Double value() {
        return value;
    }

    public Calendar date() {
        return date;
    }

    private Teacher teacher() {
        return teacher;
    }

    private Student student() {
        return student;
    }
}
