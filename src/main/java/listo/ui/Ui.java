package listo.ui;

import listo.task.Task;
import listo.task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interaction, including reading input and printing messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the UI and the Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command line from the user.
     *
     * @return The string command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome greeting message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Listo :)\nHow can I help you?");
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye Bye:) Hope to see you again soon!");
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Confirms that a task has been successfully added.
     *
     * @param t     The task that was added.
     * @param count The new total number of tasks.
     */
    public void showTaskAdded(Task t, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Confirms that a task has been deleted.
     *
     * @param t     The task that was removed.
     * @param count The remaining number of tasks in the list.
     */
    public void showTaskDeleted(Task t, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Confirms that a task has been marked as done.
     *
     * @param t The task that was marked.
     */
    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
    }

    /**
     * Confirms that a task has been marked as not done.
     *
     * @param t The task that was unmarked.
     */
    public void showTaskUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t.toString());
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The listo.task.TaskList object containing the tasks.
     */
    public void showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("Take a break! There's no tasks to be done for now.");
        } else {
            System.out.println("Things to do:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Prints the list of tasks found by a keyword search.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showFoundTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                try {
                    System.out.println((i + 1) + "." + tasks.getTask(i));
                } catch (Exception e) {
                    System.out.println("Error printing task.");
                }
            }
        }
    }

    /**
     * Displays the list of tasks found for a specific date.
     *
     * @param tasks The list of matching tasks.
     * @param dateInput The date string used for the search.
     */
    public void showTasksOnDate(ArrayList<Task> tasks, String dateInput) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found on " + dateInput);
        } else {
            System.out.println("Here are the tasks on " + dateInput + ":");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }
}