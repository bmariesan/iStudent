package exams.domain;

/**
 * Created by Teodora on 17/01/2018.
 */
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Document(collection = "grade")
public class Grade implements Serializable {
    @DBRef
    private Exam exam;
    private String title;
}
