package Laboratory_1.domain.factory;

import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.character.Mage;
import Laboratory_1.domain.models.weapon.Staff;
import Laboratory_1.domain.models.weapon.Weapon;

public class MageFactory implements CharacterWeaponFactory {
    @Override
    public Character createCharacter() {
        return new Mage();
    }

    @Override
    public Weapon createWeapon() {
        return new Staff();
    }
}
