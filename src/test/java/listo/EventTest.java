package listo;

import listo.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        // Events just treat dates as Strings (unless you upgraded them in Level-8)
        Event e = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", e.toString());
    }

    @Test
    public void testToFileFormat() {
        Event e = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("E | 0 | project meeting | Mon 2pm | 4pm", e.toFileFormat());
    }
}