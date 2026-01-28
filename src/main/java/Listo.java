import java.util.Scanner;

public class Listo {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Listo\nHow can I help you?";
        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        // Echos user input, exits when user use the command "bye"
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }

        System.out.println(goodbye);
    }
}