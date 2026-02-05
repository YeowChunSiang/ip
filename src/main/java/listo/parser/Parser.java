package listo.parser;

import listo.command.CommandType;
import listo.exception.ListoException;
import listo.task.Deadline;
import listo.task.Event;
import listo.task.Task;
import listo.task.TaskList;
import listo.task.Todo;
import listo.ui.Ui;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the logic for parsing and executing user commands.
 * Contains static methods to process specific command types.
 */
public class Parser {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Parser() {
        // empty
    }

    /**
     * Parses the user input and executes the corresponding command.
     * Maps the input string to a listo.command.CommandType enum and executes the relevant logic.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the command is invalid or execution fails.
     */
    public static void parseCommand(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ");
        String commandWord = parts[0];
        CommandType command = getCommandType(commandWord);

        switch (command) {
            case CommandType.LIST:
                ui.showList(tasks);
                break;
            case CommandType.MARK:
                handleMark(input, tasks, ui);
                break;
            case CommandType.UNMARK:
                handleUnmark(input, tasks, ui);
                break;
            case CommandType.DELETE:
                handleDelete(input, tasks, ui);
                break;
            case CommandType.TODO:
                addTodo(input, tasks, ui);
                break;
            case CommandType.DEADLINE:
                addDeadline(input, tasks, ui);
                break;
            case CommandType.EVENT:
                addEvent(input, tasks, ui);
                break;
            case CommandType.FILTER:
                handleFilter(input, tasks, ui);
                break;
            case CommandType.FIND:
                handleFind(input, tasks, ui);
                break;
            case CommandType.BYE:
                // The 'bye' command is checked in the main loop to break execution,
                break;
            default:
                throw new ListoException("OOPS!!! Sorry, I don't know what you mean :(");
        }
    }

    /**
     * Converts a string command word into a listo.command.CommandType enum.
     *
     * @param commandWord The first word of the user input.
     * @return The corresponding listo.command.CommandType, or UNKNOWN if not recognized.
     */
    private static CommandType getCommandType(String commandWord) {
        try {
            return CommandType.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

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
     * Parses the "delete" command and removes the specified task.
     * Checks if the task index exists and delegates the removal to listo.task.TaskList and UI.
     *
     * @param input The full user input string (e.g., "delete 1").
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display confirmation messages.
     * @throws ListoException If the input format is invalid or the task number does not exist.
     */
    public static void handleDelete(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Which task number do you want me to delete?\nEg. delete 1");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new ListoException("OOPS!!! I can't find that task number, can you try again?");
            }
            Task t = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showTaskDeleted(t, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new ListoException("OOPS!!! Please enter a valid number.\nEg. delete 1");
        }
    }

    /**
     * Parses the "todo" command and adds a new listo.task.Todo task.
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
     * Parses the "deadline" command and adds a new listo.task.Deadline task.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the format is incorrect (missing /by) or description is empty.
     */
    public static void addDeadline(String input, TaskList tasks, Ui ui) throws ListoException {
        if (input.trim().equals("deadline")) {
            throw new ListoException("OOPS!!! What is the name and due date for this task?\n" +
                    "Eg. deadline Do Tutorial 1 /by 04/02/2026 1800");
        }
        if (!input.contains("/by")) {
            throw new ListoException("OOPS!!! What is the due date for this task?\n" +
                    "Eg. deadline Do Tutorial 1 /by 04/02/2026 1800");
        }
        String[] parts = input.substring(8).split("/by");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! The due date cannot be empty.\n" +
                    "What is the due date for this task?\nEg. deadline Do Tutorial 1 /by 04/02/2026 1800");
        }
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! What is the name for this task?\n" +
                    "Eg. deadline Do Tutorial 1 /by 04/02/2026 1800");
        }
        String by = parts[1].trim();

        try {
            Task t = new Deadline(description, by);
            tasks.addTask(t);
            ui.showTaskAdded(t, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new ListoException("OOPS!!! Invalid date format. " +
                    "Please use DD/MM/YYYY HHmm (e.g., 04/02/2026 1800).");
        }
    }

    /**
     * Parses the "event" command and adds a new listo.task.Event task.
     *
     * @param input The full user input string.
     * @param tasks The current list of tasks.
     * @param ui    The UI instance to display messages.
     * @throws ListoException If the format is incorrect (missing /from or /to) or description is empty.
     */
    public static void addEvent(String input, TaskList tasks, Ui ui) throws ListoException {
        if (input.trim().equals("event")) {
            throw new ListoException("OOPS!!! What is the name, start and end date for this task?\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new ListoException("OOPS!!! What is the start and/or end date for this task?\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String[] parts = input.substring(5).split("/from");
        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new ListoException("OOPS!!! What is the name of the event task?\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Please enter '/from' time before '/to' time.\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String[] timeParts = parts[1].split("/to");
        if (timeParts.length == 0) {
            throw new ListoException("OOPS!!! An event must have a valid '/from' time.\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        if (timeParts.length < 2) {
            throw new ListoException("OOPS!!! An event must have a valid '/to' time.\n" +
                    "Eg. event Do Tutorial 1 /from Monday /to Tuesday");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        Task t = new Event(description, from, to);
        tasks.addTask(t);
        ui.showTaskAdded(t, tasks.getSize());
    }

    /**
     * Filters and displays tasks that occur on a specific date.
     *
     * @param input The full user command string containing the date.
     * @param tasks The current list of tasks.
     * @param ui The UI instance to print the results.
     * @throws ListoException If the date format is invalid.
     */
    public static void handleFilter(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new ListoException("OOPS!!! Please specify a date to filter by.\nEg. filter 2/12/2019");
        }

        String dateString = parts[1];
        try {
            // Parse the date string into a LocalDate object
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));

            // Get matching tasks
            ArrayList<Task> matchingTasks = tasks.getTasksOnDate(date);

            // Show results
            ui.showTasksOnDate(matchingTasks, dateString);

        } catch (java.time.format.DateTimeParseException e) {
            throw new ListoException("OOPS!!! Invalid date format. Please use d/M/yyyy (e.g., 2/12/2019).");
        }
    }

    /**
     * Handles the find command to search for tasks.
     *
     * @param input The full user command.
     * @param tasks The task list.
     * @param ui    The UI instance.
     * @throws ListoException If the keyword is missing.
     */
    public static void handleFind(String input, TaskList tasks, Ui ui) throws ListoException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ListoException("OOPS!!! The search keyword cannot be empty.\nEg. find book");
        }
        String keyword = parts[1].trim();
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}