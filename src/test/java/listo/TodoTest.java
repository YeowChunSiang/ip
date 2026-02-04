package listo;  // Same package as main code

import listo.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void testFileFormat() {
        assertEquals("T | 0 | read book", new Todo("read book").toFileFormat());
    }
}