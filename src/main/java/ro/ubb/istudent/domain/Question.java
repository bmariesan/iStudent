package ro.ubb.istudent.domain;

import java.util.Optional;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public interface Question<T> {
    public Optional<T> getAnswer();
    public Double getPoints();
}
