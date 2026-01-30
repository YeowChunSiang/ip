/**
 * Represents the various commands that the user can execute.
 * Used to map user input strings to specific actions.
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE,
    UNKNOWN // Used for invalid commands
}