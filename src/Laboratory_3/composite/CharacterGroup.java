package Laboratory_3.composite;

import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class CharacterGroup implements Character {
    private final String groupName;
    private final List<Character> characters = new ArrayList<>();

    public CharacterGroup(String groupName) {
        this.groupName = groupName;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    @Override
    public void introduce() {
        System.out.println("Group: " + groupName + " introduces its members:");
        for (Character character : characters) {
            character.introduce();
        }
    }

    @Override
    public void attackWithWeapon() {
        System.out.println("Group: " + groupName + " attacks together!");
        for (Character character : characters) {
            character.attackWithWeapon();
        }
    }

    @Override
    public void defend() {
        System.out.println("Group: " + groupName + " defends collectively!");
        for (Character character : characters) {
            character.defend();
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        System.out.println("Group: " + groupName + " equips its members with weapons:");
        for (Character character : characters) {
            character.equipWeapon(weapon);
        }
    }

    @Override
    public Weapon getWeapon() {
        return null;
    }

    @Override
    public String getName() {
        return groupName;
    }

    @Override
    public void setName(String name) {
        System.out.println("Group name change is not supported.");
    }

    @Override
    public void applyStatusEffect(String effect) {
        System.out.println("Applying status effect: " + effect + " to group: " + groupName);
        for (Character character : characters) {
            character.applyStatusEffect(effect);
        }
    }

    @Override
    public void removeStatusEffect(String effect) {
        System.out.println("Removing status effect: " + effect + " from group: " + groupName);
        for (Character character : characters) {
            character.removeStatusEffect(effect);
        }
    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("Group: " + groupName + " takes damage collectively.");
        int splitDamage = damage / characters.size();
        for (Character character : characters) {
            character.takeDamage(splitDamage);
        }
    }

    @Override
    public void restoreHealth(int amount) {
        System.out.println("Group: " + groupName + " restores health collectively.");
        for (Character character : characters) {
            character.restoreHealth(amount);
        }
    }

}
