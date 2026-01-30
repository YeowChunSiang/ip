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
        TaskList tasks = new TaskList();

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                break;
            }

            try {
                Parser.parseCommand(input, tasks, ui);
            } catch (ListoException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
}