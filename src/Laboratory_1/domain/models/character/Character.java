package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Weapon;

public interface Character {
    public String name = "";
    void introduce();
    void attackWithWeapon();
    void defend();
    void equipWeapon(Weapon weapon);

    Weapon getWeapon();

    String getName();
    void setName(String name);
}
