package ro.ubb.istudent.grading.command;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Command<T> {

    default T execute() {
        throw new UnsupportedOperationException();
    }

    default T undo()  {
        throw new UnsupportedOperationException();
    }
}
