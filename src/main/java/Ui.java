import java.util.Scanner;

/**
 * Handles all user interaction, including reading input and printing messages.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Listo :)\nHow can I help you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(Task t, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void showTaskDeleted(Task t, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
    }

    public void showTaskUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t.toString());
    }

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
}