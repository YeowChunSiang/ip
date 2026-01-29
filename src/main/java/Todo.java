/**
 * Represents a Todo task.
 * A simple task with only a description.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}