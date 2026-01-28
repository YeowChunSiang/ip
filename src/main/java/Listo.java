import java.util.Scanner;

public class Listo {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Listo :)\nHow can I help you?";
        String goodbye = "Bye. Hope to see you again soon!";

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) { // exit using bye command
                break;
            } else if (input.equals("list")) { // show all the tasks using list command
                System.out.println("Things to do:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
            } else if (input.startsWith("mark ")) { // mark a task as done using mark command
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index].toString());
            } else if (input.startsWith("unmark ")) { // mark a task as undone using unmark command
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index].toString());
            } else if (input.startsWith("todo ")) { // add a new to do task using todo command
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("deadline ")) { // add a new task with deadline using deadline command
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];

                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("event ")) { // add a new task with start and end date/time using event command
                String[] parts = input.substring(6).split(" /from ");
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];

                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("OOPS! Sorry, I don't know what you mean :(");
            }
        }

        System.out.println(goodbye);
    }
}