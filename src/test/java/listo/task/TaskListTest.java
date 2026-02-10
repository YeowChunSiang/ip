package listo.task;

import listo.exception.ListoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_increasesSize() {
        // Verifies that adding a new task correctly increments the list size count.
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void deleteTask_decreasesSize() throws ListoException {
        // Verifies that deleting a task correctly decrements the list size count.
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read"));
        tasks.deleteTask(0); // Delete the first task
        assertEquals(0, tasks.getSize());
    }
}