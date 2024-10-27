package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Weapon;

public class Archer implements Character {
    private Weapon weapon;

    @Override
    public void introduce() {
        System.out.println("I am an Archer, precise and deadly from a distance!");
    }

    @Override
    public void attackWithWeapon() {
        if (weapon != null) {
            weapon.attack();
        } else {
            System.out.println("Archer has no weapon equipped!");
        }
    }

    @Override
    public void defend() {
        System.out.println("Archer dodges the attack swiftly!");
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("Archer equips a new ranged weapon.");
    }
}
