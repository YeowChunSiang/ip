package listo;

import listo.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        // Checks if the date prints nicely (e.g., Dec 02 2019, 6:00 pm)
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Dec 02 2019, 6:00 PM)", d.toString());
    }

    @Test
    public void testToFileFormat() {
        // Checks if the file save format is correct (e.g., 2/12/2019 1800)
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("D | 0 | return book | 2/12/2019 1800", d.toFileFormat());
    }
}