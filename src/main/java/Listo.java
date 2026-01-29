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

            try {
                if (input.equals("bye")) { // Exit using "bye" command
                    break;

                } else if (input.equals("list")) { // Show all tasks using "list" command
                    ui.showList(tasks);

                } else if (input.startsWith("mark")) { // Mark a task as done using "mark" command
                    Parser.handleMark(input, tasks, ui);

                } else if (input.startsWith("unmark")) { // Mark a task as undone using "unmark" command
                    Parser.handleUnmark(input, tasks, ui);

                } else if (input.startsWith("todo")) { // Add a new Todo task using "todo" command
                    Parser.addTodo(input, tasks, ui);

                } else if (input.startsWith("deadline")) { // Add a new task with deadline using "deadline" command
                    Parser.addDeadline(input, tasks, ui);

                } else if (input.startsWith("event")) { // Add a new task with start and end date/time using "event" command
                    Parser.addEvent(input, tasks, ui);

                } else {
                    throw new ListoException("OOPS!!! Sorry, I don't know what you mean :(");
                }
            } catch (ListoException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
}