import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Manages the list of tasks.
 * Encapsulates an ArrayList of Task objects and provides methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The zero-based index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at a specific index.
     *
     * @param index The zero-based index of the task.
     * @return The Task object.
     */
    public Task getTask(int index) {

        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {

        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The zero-based index of the task.
     */
    public void markDone(int index) {

        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The zero-based index of the task.
     */
    public void markNotDone(int index) {

        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds all tasks that occur on a specific date.
     *
     * @param date The date to search for.
     * @return An ArrayList of matching tasks.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isOn(date)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * Useful for initializing the list with data loaded from storage.
     *
     * @param tasks An ArrayList of Task objects to populate the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}