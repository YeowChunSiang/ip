package listo;

import listo.parser.Parser;
import listo.task.TaskList;
import listo.ui.Ui;
import listo.exception.ListoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        try {
            // Test inputting nonsense
            Parser.parseCommand("blah", new TaskList(), new Ui());
            fail(); // The test should fail if no exception is thrown
        } catch (ListoException e) {
            assertEquals("OOPS!!! Sorry, I don't know what you mean :(", e.getMessage());
        }
    }

    @Test
    public void parseCommand_emptyTodo_exceptionThrown() {
        try {
            // Test inputting "todo" without a description
            Parser.parseCommand("todo", new TaskList(), new Ui());
            fail();
        } catch (ListoException e) {
            assertEquals("OOPS!!! What is the name of the to do task?\nEg. todo Do Tutorial 1", e.getMessage());
        }
    }
}