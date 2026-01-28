import java.util.Scanner;

public class Listo {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Listo :)\nHow can I help you?";
        String goodbye = "Bye. Hope to see you again soon!";

        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println(greeting);

        // Echos user input, exits when user use the command "bye"
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("added: " + input);
            }
        }

        System.out.println(goodbye);
    }
}