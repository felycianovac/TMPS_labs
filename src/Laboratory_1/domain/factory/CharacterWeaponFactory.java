package Laboratory_1.domain.factory;

import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;

public interface CharacterWeaponFactory {
    Character createCharacter();
    Weapon createWeapon();
}
