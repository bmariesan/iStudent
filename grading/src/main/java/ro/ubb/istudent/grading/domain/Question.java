package ro.ubb.istudent.grading.domain;

import java.util.Optional;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public interface Question<T> {
    Optional<T> getAnswer();
    Double getPoints();
}
