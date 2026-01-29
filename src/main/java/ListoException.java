/**
 * Represents exceptions specific to the Listo chatbot.
 * Used for handling user input errors and logical constraints.
 */
public class ListoException extends Exception {
    public ListoException(String message) {
        super(message);
    }
}