package Laboratory_2.command;

import Laboratory_1.domain.models.character.Character;

public class DefendCommand implements Command {
    private Character character;

    public DefendCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.defend();
    }

    @Override
    public void undo() {
        System.out.println(character.getName() + "'s defense action was undone.");
    }
}
