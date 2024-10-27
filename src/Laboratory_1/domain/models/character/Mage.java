package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;

public class Mage implements Character {
    private Weapon weapon;

    @Override
    public void introduce() {
        System.out.println("I am a Mage, master of magical arts!");
    }

    @Override
    public void attackWithWeapon() {
        if (weapon != null) {
            weapon.attack();
        } else {
            System.out.println("Mage has no weapon equipped!");
        }
    }

    @Override
    public void defend() {
        System.out.println("Mage casts a protective barrier!");
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("Mage equips a new magical weapon.");
    }



}
