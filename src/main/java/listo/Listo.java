package listo;

import listo.exception.ListoException;
import listo.parser.Parser;
import listo.storage.Storage;
import listo.task.TaskList;
import listo.ui.Ui;

import java.io.IOException;

/**
 * The listo.Listo class is a simple chatbot that helps users manage tasks.
 * It initializes the core components (listo.ui.Ui, listo.storage.Storage, listo.task.TaskList)
 * and runs the main command loop.
 */
public class Listo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new listo.Listo application.
     * Initializes the UI, listo.storage.Storage, and listo.task.TaskList.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Listo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Continuously reads user commands and delegates execution to the listo.parser.Parser
     * until the "bye" command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            if (input.equals("bye")) {
                isExit = true;
            }
            try {
                Parser.parseCommand(input, tasks, ui);
                storage.save(tasks);
            } catch (ListoException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Listo("./data/listo.txt").run();
    }
}