package listo;

import listo.task.Task;
import listo.task.Todo; // We use Todo to test the abstract Task class
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task t = new Todo("test task");
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void testMarkAsUndone() {
        Task t = new Todo("test task");
        t.markAsDone();   // Mark it first
        t.markAsNotDone(); // Then unmark it
        assertEquals(" ", t.getStatusIcon());
    }
}