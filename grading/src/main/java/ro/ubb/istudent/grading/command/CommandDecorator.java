package ro.ubb.istudent.grading.command;

import javax.annotation.concurrent.Immutable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Immutable
public final class CommandDecorator<T> implements Command<T> {

    private final Command<T> command;

    public CommandDecorator(final Command<T> command) {
        this.command = command;
    }

    @Override
    public T execute() {
        return command.execute();
    }

    @Override
    public T undo() {
        return command.undo();
    }
}
