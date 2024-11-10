package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Weapon;

public interface Character {
    void introduce();
    void attackWithWeapon();
    void defend();
    void equipWeapon(Weapon weapon);

    Weapon getWeapon();

    String getName();
    void setName(String name);

    void applyStatusEffect(String effect);
    void removeStatusEffect(String effect);

    void takeDamage(int damage);
    void restoreHealth(int amount);
}
