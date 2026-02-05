package listo.task;

/**
 * Represents an listo.task.Event task.
 * A task that occurs over a specific time period.
 */
public class Event extends Task {
    /** The start time of the event. */
    protected String from;
    /** The end time of the event. */
    protected String to;

    /**
     * Creates a new listo.task.Event task.
     *
     * @param description The event description.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the listo.task.Event task.
     * Prepends "[E]" and appends the time period.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string format of the listo.task.Event task for saving to a file.
     * Appends the type identifier "E", start time, and end time to the standard task format.
     *
     * @return A string representation of the listo.task.Event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }
}