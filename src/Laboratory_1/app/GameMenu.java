package Laboratory_1.app;

import Laboratory_1.domain.models.weapon.Axe;
import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.singleton.GameWorld;
import Laboratory_1.domain.factory.*;
import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;
import Laboratory_2.command.*;
import Laboratory_2.observer.Observer;
import Laboratory_3.adapter.AxeAdapter;
import Laboratory_3.composite.CharacterGroup;
import Laboratory_3.decorator.DefenseBoostDecorator;
import Laboratory_3.decorator.EnhancedAttackDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<CloneableCharacter> characters = new ArrayList<>();
    private final GameWorld gameWorld = GameWorld.getInstance();
    private final CommandHistory commandHistory = new CommandHistory();
    private final List<CharacterGroup> characterGroups = new ArrayList<>();


    public void start() {
        System.out.println("Welcome to the RPG Game Console! Customize your characters, manage your game world, and enjoy an interactive experience.");
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createCharacter();
                case 2 -> cloneCharacter();
                case 3 -> gameWorld.displaySettings();
                case 4 -> updateGameWorldDifficulty();
                case 5 -> updateGameWorldName();
                case 6 -> displayAllCharacters();
                case 7 -> manageCharacterActions();
                case 8 -> undoLastAction();
                case 9 -> replayAllActions();
                case 10 -> applyBuffToCharacter();
                case 11 -> equipSpecialTool();
                case 12 -> createGroup();
                case 13 -> addCharacterToGroup();
                case 14 -> performGroupOperation();
                case 15 -> exitGame();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== RPG Game Console ===");
        System.out.println("1. Create a new Character");
        System.out.println("2. Clone an Existing Character");
        System.out.println("3. View Game World Settings");
        System.out.println("4. Update Game World Level");
        System.out.println("5. Update Game World Name");
        System.out.println("6. Display All Characters");
        System.out.println("7. Manage Character Actions");
        System.out.println("8. Undo Last Action");
        System.out.println("9. Replay All Actions");
        System.out.println("10. Apply Buff to Character");
        System.out.println("11. Equip Special Tool");
        System.out.println("12. Create a New Group");
        System.out.println("13. Add Character to a Group");
        System.out.println("14. Perform Group Operation");
        System.out.println("15. Exit");
        System.out.print("Select an option: ");
    }

    private void createCharacter() {
        System.out.println("\nChoose a Character Type:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        CharacterWeaponFactory factory = switch (choice) {
            case 1 -> new WarriorFactory();
            case 2 -> new MageFactory();
            case 3 -> new ArcherFactory();
            default -> {
                System.out.println("Invalid choice. Please select a valid character type.");
                yield null;
            }
        };

        if (factory == null) return;

        Character character = factory.createCharacter();

        System.out.print("Enter a name for your character: ");
        String name = scanner.nextLine();
        character.setName(name);
        System.out.println(name + " has joined the adventure!");

        Weapon weapon = factory.createWeapon();
        character.equipWeapon(weapon);
        character.introduce();

        if (character instanceof CloneableCharacter cloneableCharacter) {
            characters.add(cloneableCharacter);
            gameWorld.addObserver((Observer) cloneableCharacter);
        }
    }

    private void cloneCharacter() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to clone. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Clone:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName());
        }

        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        CloneableCharacter originalCharacter = characters.get(choice - 1);
        CloneableCharacter clonedCharacter = originalCharacter.clone();

        if (clonedCharacter != null) {
            characters.add(clonedCharacter);
            System.out.println("Successfully cloned " + originalCharacter.getName() + "!");
        } else {
            System.out.println("Failed to clone character.");
        }
    }

    private void updateGameWorldDifficulty() {
        System.out.print("Enter new difficulty level (1-10): ");
        int newDifficulty = scanner.nextInt();
        scanner.nextLine();

        if (newDifficulty < 1 || newDifficulty > 10) {
            System.out.println("Difficulty level must be between 1 and 10. Try again.");
            return;
        }

        gameWorld.setLevel(newDifficulty);
        System.out.println("Game world difficulty updated successfully.");
    }

    private void displayAllCharacters() {
        if (characters.isEmpty()) {
            System.out.println("No characters available.");
            return;
        }

        System.out.println("\n=== All Characters ===");
        for (CloneableCharacter character : characters) {
            System.out.println("- " + character.getName() + " the " + character.getClass().getSimpleName());
        }
    }

    private void updateGameWorldName() {
        System.out.print("Enter a new name for the game world: ");
        String newName = scanner.nextLine();

        gameWorld.setName(newName);
        System.out.println("Game world name updated successfully to " + newName);
        gameWorld.displaySettings();
    }

    private void manageCharacterActions() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to manage actions. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Manage:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName());
        }

        System.out.print("Select a character: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Character character = characters.get(choice - 1);

        System.out.println("\nChoose an Action:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Special Ability");
        System.out.print("Select an action: ");
        int actionChoice = scanner.nextInt();
        scanner.nextLine();

        Command command;
        switch (actionChoice) {
            case 1 -> {
                System.out.println("Choose a target for the attack:");
                Character target = selectTarget(character);
                if (target == null) return;
                command = new AttackCommand(character, target);
            }
            case 2 -> command = new DefendCommand(character);
            case 3 -> command = new SpecialAbilityCommand(character);
            default -> {
                System.out.println("Invalid action. Returning to main menu.");
                return;
            }
        }

        commandHistory.executeCommand(command);
    }


    private Character selectTarget(Character attacker) {
        if (characters.size() < 2) {
            System.out.println("There must be at least two characters to select a target.");
            return null;
        }

        System.out.println("\nAvailable Targets:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName());
        }

        Character target = null;
        while (target == null) {
            System.out.print("Select a target: ");
            int targetChoice = scanner.nextInt();
            scanner.nextLine();

            if (targetChoice < 1 || targetChoice > characters.size()) {
                System.out.println("Invalid choice.");
            } else {
                target = characters.get(targetChoice - 1);
                if (target == attacker) {
                    System.out.println("A character cannot attack themselves. Please select a different target.");
                    target = null;
                }
            }
        }

        return target;
    }


    private void undoLastAction() {
        commandHistory.undoLastCommand();
    }

    private void replayAllActions() {
        commandHistory.replayCommands();
    }

    private void exitGame() {
        System.out.println("Are you sure you want to exit? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            System.out.println("Exiting game...");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Returning to game menu.");
        }
    }

    private void applyBuffToCharacter() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to buff. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Buff:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName());
        }

        System.out.print("Select a character: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Character character = characters.get(choice - 1);

        System.out.println("\nChoose a Buff:");
        System.out.println("1. Enhanced Attack (+20 damage for 3 turns)");
        System.out.println("2. Defense Boost (-15 damage taken for 2 turns)");
        System.out.print("Select a buff: ");
        int buffChoice = scanner.nextInt();
        scanner.nextLine();

        Character decoratedCharacter;
        switch (buffChoice) {
            case 1 -> decoratedCharacter = new EnhancedAttackDecorator(character, 20);
            case 2 -> decoratedCharacter = new DefenseBoostDecorator(character, 15);
            default -> {
                System.out.println("Invalid buff. Returning to main menu.");
                return;
            }
        }

        characters.set(choice - 1, (CloneableCharacter) decoratedCharacter);
        System.out.println(character.getName() + " has received a buff!");
    }

    private void equipSpecialTool() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to equip a tool. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Equip a Special Tool:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName());
        }

        System.out.print("Select a character: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Character character = characters.get(choice - 1);

        System.out.println("\nChoose a Special Tool:");
        System.out.println("1. Axe");
        System.out.print("Select a tool: ");
        int toolChoice = scanner.nextInt();
        scanner.nextLine();

        Weapon specialTool = switch (toolChoice) {
            case 1 -> new AxeAdapter(new Axe());
            default -> {
                System.out.println("Invalid tool choice. Returning to main menu.");
                yield null;
            }
        };

        if (specialTool != null) {
            character.equipWeapon(specialTool);
//            System.out.println(character.getName() + " has equipped a " + specialTool.getName() + "!");
        }
    }

    private void createGroup() {
        System.out.print("Enter a name for the group: ");
        String groupName = scanner.nextLine();
        CharacterGroup group = new CharacterGroup(groupName);
        characterGroups.add(group);
        System.out.println("Group " + groupName + " created!");
    }

    private void addCharacterToGroup() {
        if (characters.isEmpty() || characterGroups.isEmpty()) {
            System.out.println("Either no characters or no groups exist. Create them first.");
            return;
        }

        System.out.println("Select a character to add to a group:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getName() + " the " + characters.get(i).getClass().getSimpleName());
        }

        int charChoice = scanner.nextInt();
        scanner.nextLine();

        if (charChoice < 1 || charChoice > characters.size()) {
            System.out.println("Invalid character choice.");
            return;
        }

        Character character = characters.get(charChoice - 1);

        System.out.println("Select a group:");
        for (int i = 0; i < characterGroups.size(); i++) {
            System.out.println((i + 1) + ". " + characterGroups.get(i).getName());
        }

        int groupChoice = scanner.nextInt();
        scanner.nextLine();

        if (groupChoice < 1 || groupChoice > characterGroups.size()) {
            System.out.println("Invalid group choice.");
            return;
        }

        CharacterGroup group = characterGroups.get(groupChoice - 1);
        group.addCharacter(character);
        System.out.println(character.getName() + " added to group " + group.getName());
    }

    private void performGroupOperation() {
        if (characterGroups.isEmpty()) {
            System.out.println("No groups available. Create a group first.");
            return;
        }

        System.out.println("Select a group to perform an operation:");
        for (int i = 0; i < characterGroups.size(); i++) {
            System.out.println((i + 1) + ". " + characterGroups.get(i).getName());
        }

        System.out.print("Choose the acting group: ");
        int actingGroupIndex = scanner.nextInt();
        scanner.nextLine();

        if (actingGroupIndex < 1 || actingGroupIndex > characterGroups.size()) {
            System.out.println("Invalid group choice.");
            return;
        }

        CharacterGroup actingGroup = characterGroups.get(actingGroupIndex - 1);

        System.out.println("Select a target group for the operation:");
        for (int i = 0; i < characterGroups.size(); i++) {
            if (i != (actingGroupIndex - 1)) {
                System.out.println((i + 1) + ". " + characterGroups.get(i).getName());
            }
        }

        System.out.print("Choose the target group: ");
        int targetGroupIndex = scanner.nextInt();
        scanner.nextLine();

        if (targetGroupIndex < 1 || targetGroupIndex > characterGroups.size() || targetGroupIndex == actingGroupIndex) {
            System.out.println("Invalid target group choice.");
            return;
        }

        CharacterGroup targetGroup = characterGroups.get(targetGroupIndex - 1);

        System.out.println("Choose an operation:");
        System.out.println("1. Group Attack");
        System.out.println("2. Group Defend");
        int operation = scanner.nextInt();
        scanner.nextLine();

        switch (operation) {
            case 1 -> {
                actingGroup.setTargetGroup(targetGroup);
                actingGroup.attackWithWeapon();

            }
            case 2 -> {
                System.out.println("Group " + actingGroup.getName() + " is defending!");
                actingGroup.defend();
            }
            default -> System.out.println("Invalid operation.");
        }
    }


}
