package Laboratory_1.domain.models.character;

import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;

public class Warrior implements Character, CloneableCharacter {
    private Weapon weapon;

    @Override
    public void introduce() {
        System.out.println("I am a warrior, strong and brave!");
    }

    @Override
    public void attackWithWeapon() {
        if (weapon != null) {
            weapon.attack();
        } else {
            System.out.println("Warrior has no weapon equipped!");
        }
    }

    @Override
    public void defend() {
        System.out.println("Warrior blocks the attack with a shield!");
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("Warrior equips a new weapon.");
    }

    @Override
    public CloneableCharacter clone() {
        try {
            Warrior clonedWarrior = (Warrior) super.clone();
            return clonedWarrior;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

