/**
 * Handles the logic for parsing and executing user commands.
 * Contains static methods to process specific command types.
 */
public class Parser {

    /**
     * Parses the "mark" command to mark a task as done.
     *
     * @param input The full user input string (e.g., "mark 1").
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the input format is invalid or the index is out of bounds.
     */
    public static void handleMark(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Which task number do you want me to mark?\nEg. mark 1");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number, can you try again?");
            }
            tasks.markDone(index);
            ui.showTaskMarked(tasks.getTask(index));
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number.\nEg. mark 1");
        }
    }

    /**
     * Parses the "unmark" command to mark a task as not done.
     *
     * @param input The full user input string (e.g., "unmark 1").
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the input format is invalid or the index is out of bounds.
     */
    public static void handleUnmark(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Which task number do you want me to unmark?\nEg. unmark 1");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number, can you try again?");
            }
            tasks.markNotDone(index);
            ui.showTaskUnmarked(tasks.getTask(index));
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number.\nEg. unmark 1");
        }
    }

    /**
     * Parses the "todo" command and adds a new Todo task.
     *
     * @param input The full user input string (e.g., "todo read book").
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the description is empty.
     */
    public static void addTodo(String input, TaskList tasks, Ui ui) throws ListoException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! What is the name of the to do task?\nEg. todo Do Tutorial 1");
        }
        Task t = new Todo(description);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }

    /**
     * Parses the "deadline" command and adds a new Deadline task.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the format is incorrect (missing /by) or description is empty.
     */
    public static void addDeadline(String input, TaskList tasks, Ui ui) throws ListoException {
        if (input.trim().equals("deadline")) {
            throw new ListoException("OOPS!!! What is the name and due date for this task?\nEg. deadline Do Tutorial 1 /by Monday");
        }
        if (!input.contains("/by")) {
            throw new ListoException("OOPS!!! What is the due date for this task?\nEg. deadline Do Tutorial 1 /by Monday");
        }
        String[] parts = input.substring(8).split("/by");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! The due date cannot be empty.\nWhat is the due date for this task?\nEg. deadline Do Tutorial 1 /by Monday");
        }
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! What is the name for this task?\nEg. deadline Do Tutorial 1 /by Monday");
        }
        String by = parts[1].trim();
        Task t = new Deadline(description, by);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }

    /**
     * Parses the "event" command and adds a new Event task.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the format is incorrect (missing /from or /to) or description is empty.
     */
    public static void addEvent(String input, TaskList tasks, Ui ui) throws ListoException {
        if (input.trim().equals("event")) {
            throw new ListoException("OOPS!!! What is the name, start and end date for this task?\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new ListoException("OOPS!!! What is the start and/or end date for this task?\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String[] parts = input.substring(5).split("/from");
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! What is the name of the event task?\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Please enter '/from' time before '/to' time.\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String[] timeParts = parts[1].split("/to");
        if (timeParts.length == 0) {
            throw new ListoException("OOPS!!! An event must have a valid '/from' time.\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (timeParts.length < 2) {
            throw new ListoException("OOPS!!! An event must have a valid '/to' time.\nEg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        Task t = new Event(description, from, to);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }
}