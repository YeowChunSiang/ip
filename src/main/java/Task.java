/**
 * Represents a generic task.
 * Serves as the base class for Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with a description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if done, " " (space) if not done.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string containing status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string format of the task for saving to a file.
     *
     * @return The formatted string representation.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
