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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }
}