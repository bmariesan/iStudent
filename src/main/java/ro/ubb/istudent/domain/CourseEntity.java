package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.exception.IllegalOperationException;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
@Getter
@Setter
@ToString(exclude = {"registeredStudents"}, callSuper = true)
public class CourseEntity extends BaseEntity {
    @Indexed(unique = true)
    private String name;

    private Integer studentLimit;
    private boolean active;

    @DBRef(lazy = true)
    private List<StudentEntity> registeredStudents;

    @DBRef
    private TeacherEntity teacher;

    public List<StudentEntity> getRegisteredStudents() {
        if (registeredStudents == null) {
            return new ArrayList<>();
        }
        return registeredStudents;
    }

    public boolean isAvailableForStudent(StudentEntity student) {
        return hasAvailableSlots() && !isStudentRegistered(student);
    }

    private boolean hasAvailableSlots() {
        return getRegisteredStudents().size() < studentLimit;
    }

    private boolean isStudentRegistered(StudentEntity student) {
        for (StudentEntity registeredStudent : getRegisteredStudents()) {
            if (registeredStudent.getUsername().equals(student.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void registerStudent(StudentEntity student) {
        if (!hasAvailableSlots()) {
            throw new IllegalOperationException("The course " + name + " has reached its limit of students!");
        }

        if (isStudentRegistered(student)) {
            throw new IllegalOperationException("The student " + student.getUsername() + " is already subscribed " +
                    "to the course " + name);
        }

        List<StudentEntity> students = getRegisteredStudents();
        students.add(student);
        setRegisteredStudents(students);
    }
}
