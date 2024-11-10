package Laboratory_2.command;

import Laboratory_1.domain.models.character.Character;
import Laboratory_2.command.Command;

public class SpecialAbilityCommand implements Command {
    private Character character;

    public SpecialAbilityCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        System.out.println(character.getName() + " uses their special ability!");
    }

    @Override
    public void undo() {
        System.out.println(character.getName() + "'s special ability action was undone.");
    }
}
