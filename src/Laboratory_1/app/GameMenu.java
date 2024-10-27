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
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createCharacter();
                case 2 -> cloneCharacter();
                case 3 -> gameWorld.displaySettings();
                case 4 -> updateGameWorldDifficulty();
                case 5 -> displayAllCharacters();
                case 6 -> exitGame();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== RPG Game Console ===");
        System.out.println("1. Create a new Character");
        System.out.println("2. Clone an Existing Character");
        System.out.println("3. View Game World Settings");
        System.out.println("4. Update Game World Difficulty");
        System.out.println("5. Display All Characters");
        System.out.println("6. Exit");
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
                System.out.println("Invalid choice.");
                yield null;
            }
        };

        if (factory == null) return;

        Character character = factory.createCharacter();
        Weapon weapon = factory.createWeapon();
        character.equipWeapon(weapon);
        character.introduce();
        character.attackWithWeapon();

        // Store the character in the list for cloning
        if (character instanceof CloneableCharacter cloneableCharacter) {
            characters.add(cloneableCharacter);
            System.out.println("Character created and equipped with a weapon.");
        }
    }

    private void cloneCharacter() {
        if (characters.isEmpty()) {
            System.out.println("No characters available to clone. Create a character first.");
            return;
        }

        System.out.println("\nChoose a Character to Clone:");
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

        CloneableCharacter originalCharacter = characters.get(choice - 1);
        CloneableCharacter clonedCharacter = originalCharacter.clone();

        if (clonedCharacter != null) {
            characters.add(clonedCharacter);
            System.out.println("Character cloned successfully.");
            clonedCharacter.introduce();
        } else {
            System.out.println("Failed to clone character.");
        }
    }

    private void updateGameWorldDifficulty() {
        System.out.print("Enter new difficulty level (1-10): ");
        int newDifficulty = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        gameWorld.setLevel(newDifficulty);
        System.out.println("Game world difficulty updated.");
        gameWorld.displaySettings();
    }

    private void displayAllCharacters() {
        if (characters.isEmpty()) {
            System.out.println("No characters available.");
            return;
        }

        System.out.println("\n=== All Characters ===");
        for (CloneableCharacter character : characters) {
            character.introduce();
        }
    }

    private void exitGame() {
        System.out.println("Exiting game...");
        scanner.close();
        System.exit(0);
    }
}
