package ro.ubb.istudent.grading.exam;

import java.io.Serializable;
import java.util.List;
/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public interface Question extends Serializable {
    List<String> answers();
    List<String> allAnswers();
    Boolean isCorrect(final List<String> answers);
    Double points();
}
