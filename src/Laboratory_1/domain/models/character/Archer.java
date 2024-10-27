package Laboratory_1.domain.models.character;

import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;

public class Archer implements Character, CloneableCharacter {
    private Weapon weapon;
    private String name;

    @Override
    public void introduce() {
        System.out.println("I am " + name +  ", precise and deadly from a distance!");
    }

    @Override
    public void attackWithWeapon() {
        if (weapon != null) {
            weapon.attack();
        } else {
            System.out.println(this.name + " has no weapon equipped!");
        }
    }

    @Override
    public void defend() {
        System.out.println(this.name + " dodges the attack!");
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("My special tool is " + weapon.getName() + "!");
    }

    @Override
    public CloneableCharacter clone() {
        try {
            Archer clonedArcher = (Archer) super.clone();
            return clonedArcher;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
