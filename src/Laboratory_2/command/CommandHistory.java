package Laboratory_2.command;

import java.util.Stack;
public class CommandHistory {
    private final Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }

    public void replayCommands() {
        System.out.println("Replaying all commands:");
        for (Command command : history) {
            command.execute();
        }
    }
}

