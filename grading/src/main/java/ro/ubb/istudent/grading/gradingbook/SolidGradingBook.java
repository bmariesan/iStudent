package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Marius on 10.12.2017.
 */

@ToString
@Document(collection = "grading_book")
@EqualsAndHashCode(of = {"id"})
public class SolidGradingBook implements GradingBook {

    @Id
    @JsonProperty
    public final ObjectId id;

    @JsonProperty
    public final Calendar created;

    @JsonProperty
    public final Calendar expiration;

    @JsonProperty
    public final List<Grade> grades;

    public SolidGradingBook() {
        this(ObjectId.get(), new ArrayList<>());
    }

    public SolidGradingBook(final ObjectId id, final List<Grade> grades) {
        this.id = id;
        this.created = Calendar.getInstance();
        this.expiration = Calendar.getInstance();
        this.grades = grades;
    }

    public SolidGradingBook(List<Grade> grades) {
        this(ObjectId.get(), grades);
    }

    @Override
    public ObjectId id() {
        return id;
    }

    @Override
    public Calendar created() {
        return created;
    }

    @Override
    public Calendar expiration() {
        return expiration;
    }

    @Override
    public List<Grade> grades() {
        return grades;
    }

    @Override
    public GradingBook storeGrade(final Grade grade) {
        return new SolidGradingBook(ImmutableList.<Grade>builder()
                .addAll(grades).add(grade).build());
    }

    @Override
    public Optional<Grade> getGradeById(final ObjectId id) {
        return grades.stream().filter(it -> it.id().equals(id)).findFirst();
    }

    @Override
    public GradingBook deleteGrade(final ObjectId gradeId) {
        return new SolidGradingBook(grades.stream()
                .filter(it -> !it.id().equals(gradeId))
                .collect(Collectors.toList()));
    }
}
