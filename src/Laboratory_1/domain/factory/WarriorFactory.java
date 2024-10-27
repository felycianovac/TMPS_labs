package Laboratory_1.domain.factory;

import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.character.Warrior;
import Laboratory_1.domain.models.weapon.Sword;
import Laboratory_1.domain.models.weapon.Weapon;

public class WarriorFactory implements CharacterWeaponFactory {
    @Override
    public Character createCharacter() {
        return new Warrior();
    }

    @Override
    public Weapon createWeapon() {
        return new Sword();
    }
}
