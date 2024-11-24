package Laboratory_3.composite;

import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class CharacterGroup implements Character {
    private final String groupName;
    private final List<Character> characters = new ArrayList<>();
    private CharacterGroup targetGroup;


    public CharacterGroup(String groupName) {
        this.groupName = groupName;
    }

    public void setTargetGroup(CharacterGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    public List<Character> getCharacters() {
        return characters;
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
    public int attackWithWeapon() {
        if (characters.isEmpty()) {
            System.out.println("Group: " + groupName + " has no members to attack!");
            return 0;
        }

        if (targetGroup == null || targetGroup.getCharacters().isEmpty()) {
            System.out.println("No target group available. Attacking unspecified targets.");
            int totalDamageDealt = 0;

            for (Character attacker : characters) {
                int damageDealt = attacker.attackWithWeapon();
                System.out.println(attacker.getName() + " dealt " + damageDealt + " damage.");
                totalDamageDealt += damageDealt;
            }

            return totalDamageDealt;
        }

        System.out.println("Group: " + groupName + " launches a coordinated attack!");
        List<Character> targets = targetGroup.getCharacters();
        int totalDamageDealt = 0;
        int targetIndex = 0;

        for (Character attacker : characters) {
            Character target = targets.get(targetIndex);

            int damageDealt = attacker.attackWithWeapon();
            System.out.println(attacker.getName() + " attacks " + target.getName() + " for " + damageDealt + " damage!");
            target.takeDamage(damageDealt);

            totalDamageDealt += damageDealt;

            targetIndex = (targetIndex + 1) % targets.size();
        }

        System.out.println("Group: " + groupName + " dealt a total of " + totalDamageDealt + " damage!");
        return totalDamageDealt;
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
