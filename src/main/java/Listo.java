import java.io.IOException;

/**
 * The Listo class is a simple chatbot that helps users manage tasks.
 * It supports adding Todo, Deadline, and Event tasks, as well as marking them as done/undone.
 */
public class Listo {

    /**
     * The entry point of the application.
     * Continuously reads user commands and delegates execution to the Parser
     * until the "bye" command is received.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        // 1. Initialize Storage with a relative path
        Storage storage = new Storage("./data/listo.txt");
        TaskList tasks;

        // 2. Try to load existing tasks
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                break;
            }

            try {
                // 3. Parse command
                Parser.parseCommand(input, tasks, ui);
                // 4. Save after every command (to ensure data is safe)
                storage.save(tasks);
            } catch (ListoException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
}