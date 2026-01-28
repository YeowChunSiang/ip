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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
            } else if (input.startsWith("mark ")) { // mark a task as done using mark command
                // Extract the number (e.g., "mark 2" -> 2)
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index].toString());
            } else if (input.startsWith("unmark ")) { // mark a task as undone using unmark command
                // Extract the number (e.g., "unmark 2" -> 2)
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index].toString());
            } else { // add a new task
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
        }

        System.out.println(goodbye);
    }
}