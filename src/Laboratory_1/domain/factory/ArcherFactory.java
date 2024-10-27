package Laboratory_1.domain.factory;

import Laboratory_1.domain.models.character.Archer;
import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Bow;
import Laboratory_1.domain.models.weapon.Weapon;

public class ArcherFactory implements CharacterWeaponFactory {
    @Override
    public Character createCharacter() {
        return new Archer();
    }

    @Override
    public Weapon createWeapon() {
        return new Bow();
    }
}
