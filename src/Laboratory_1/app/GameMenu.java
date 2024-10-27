package Laboratory_1.app;

import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.singleton.GameWorld;
import Laboratory_1.domain.factory.*;
import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<CloneableCharacter> characters = new ArrayList<>();
    private final GameWorld gameWorld = GameWorld.getInstance(); // Singleton instance

    public void start() {
        System.out.println("Welcome to the RPG Game Console! Customize your characters, manage your game world, and enjoy an interactive experience.");
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createCharacter();
                case 2 -> cloneCharacter();
                case 3 -> gameWorld.displaySettings();
                case 4 -> updateGameWorldDifficulty();
                case 5 -> updateGameWorldName();
                case 6 -> displayAllCharacters();
                case 7 -> manageCharacterWeapon();
                case 8 -> exitGame();
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
        System.out.println("7. Manage Character Weapon");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    private void createCharacter() {
        System.out.println("\nChoose a Character Type:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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
        character.setName(name); // Set the name of the character
        System.out.println(name + " has joined the adventure!");
        Weapon weapon = factory.createWeapon();
        System.out.println("-----------------------------------");

//        Weapon weapon = factory.createWeapon();
//        character.equipWeapon(weapon);

        character.introduce();
        character.equipWeapon(weapon);

//        character.attackWithWeapon();

        if (character instanceof CloneableCharacter cloneableCharacter) {
            characters.add(cloneableCharacter);
//            System.out.println("Your character " + name + " has been equipped with a " + weapon.getClass().getSimpleName() + ".");
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
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        CloneableCharacter originalCharacter = characters.get(choice - 1);
        CloneableCharacter clonedCharacter = originalCharacter.clone();

        if (clonedCharacter != null) {
            characters.add(clonedCharacter);
            System.out.println("Successfully cloned " + originalCharacter.getName() + "!");
//            clonedCharacter.introduce();
        } else {
            System.out.println("Failed to clone character.");
        }
    }

    private void updateGameWorldDifficulty() {
        System.out.print("Enter new difficulty level (1-10): ");
        int newDifficulty = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (newDifficulty < 1 || newDifficulty > 10) {
            System.out.println("Difficulty level must be between 1 and 10. Try again.");
            return;
        }

        gameWorld.setLevel(newDifficulty);
        System.out.println("Game world difficulty updated successfully.");
//        gameWorld.displaySettings();
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


    private void manageCharacterWeapon() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to manage weapons. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Manage:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getClass().getSimpleName());
        }

        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > characters.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Character character = characters.get(choice - 1);
        Weapon weapon = character.getWeapon();

        System.out.println("\nChoose a Weapon Action:");
        System.out.println("1. Attack");
        System.out.println("2. Special Attack");
        System.out.println("3. Reduce Durability");
        System.out.println("4. Repair Weapon");
        System.out.print("Select an option: ");
        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (actionChoice) {
            case 1 -> weapon.attack();
            case 2 -> weapon.specialAttack();
            case 3 -> {
                System.out.print("Enter amount to reduce durability by: ");
                int reduction = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                weapon.reduceDurability(reduction);
//                System.out.println("Weapon durability reduced to " + weapon.getDurability());
            }
            case 4 -> {
                weapon.repair();
//                System.out.println("Weapon has been repaired to full durability.");
            }
            default -> System.out.println("Invalid action. Returning to main menu.");
        }
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
}
