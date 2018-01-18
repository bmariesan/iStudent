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
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Marius on 10.12.2017.
 */

@ToString
@Document(collection = "grading_book")
@EqualsAndHashCode(of = {"id"})
public class GradingBook implements Serializable {

    @Id
    @JsonProperty
    public final ObjectId id;

    @JsonProperty
    public final Calendar created;

    @JsonProperty
    public final Calendar expiration;

    @JsonProperty
    public final List<Grade> grades;

    @JsonProperty
    public final List<Teacher> teachers;

    public GradingBook() {
        this(ObjectId.get(), null, null);
    }

    public GradingBook(
            final ObjectId id,
            final List<Grade> grades,
            final List<Teacher> teachers) {
        this.id = id;
        this.created = Calendar.getInstance();
        this.expiration = Calendar.getInstance();
        this.grades = grades;
        this.teachers = teachers;
    }
}
