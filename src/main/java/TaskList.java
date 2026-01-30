import java.util.ArrayList;

/**
 * Manages the list of tasks.
 * Encapsulates an ArrayList of Task objects and provides methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }
}