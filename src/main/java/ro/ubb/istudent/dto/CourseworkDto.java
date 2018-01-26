package ro.ubb.istudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created on 26.01.2018.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseworkDto extends BaseDto {
    public enum Type {
        Exam("exam"), Assignment("assignment");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private String name;
    private Type type;

    private boolean completed;
    private int grade;
}
