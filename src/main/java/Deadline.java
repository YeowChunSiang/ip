import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A task that needs to be done by a specific date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The task description.
     * @param by          The deadline date/time (must be in d/M/yyyy HHmm format).
     */
    public Deadline(String description, String by) {
        super(description);
        // Define the format: 2/12/2019 1800 -> d/M/yyyy HHmm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns the string format of the Deadline task for saving to a file.
     * We save it in the same format (d/M/yyyy HHmm) so the constructor can load it back easily.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.toFileFormat() + " | " + by.format(formatter);
    }

    /**
     * Returns the string representation of the Deadline task.
     * Displays the date in a nice format like "Dec 02 2019, 6:00 pm".
     */
    @Override
    public String toString() {
        // Output format: Dec 02 2019, 6:00 pm
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}