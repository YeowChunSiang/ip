/**
 * Represents a Deadline task.
 * A task that needs to be done by a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The task description.
     * @param by          The deadline date/time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     * Prepends "[D]" and appends the due date.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string format of the Deadline task for saving to a file.
     * Appends the type identifier "D" and the due date to the standard task format.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }
}