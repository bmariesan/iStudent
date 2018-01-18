package ro.ubb.istudent.grading.gradingbook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.ietf.jgss.Oid;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
    private final Teacher teacher;

    @JsonProperty
    public final Student student;

    public NormalGrade() {
        this(ObjectId.get(), 0.0, null, null);
    }

    public NormalGrade(
            final ObjectId id,
            final Double value,
            final Teacher teacher,
            final Student student) {
        this.id = id;
        this.value = value;
        this.date = Calendar.getInstance();
        this.teacher = teacher;
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
    public Teacher teacher() {
        return this.teacher;
    }

    @Override
    public Student student() {
        return this.student;
    }
}
