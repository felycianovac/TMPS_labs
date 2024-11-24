package Laboratory_3.decorator;


import Laboratory_1.domain.models.character.Character;
import Laboratory_1.domain.models.weapon.Weapon;
import Laboratory_1.domain.prototype.CloneableCharacter;

public abstract class CharacterDecorator implements Character, CloneableCharacter {
    protected Character character;

    public CharacterDecorator(Character character) {
        this.character = character;
    }

    @Override
    public void introduce() {
        character.introduce();
    }

    @Override
    public int attackWithWeapon() {
        return character.attackWithWeapon();
    }

    @Override
    public void defend() {
        character.defend();
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        character.equipWeapon(weapon);
    }

    @Override
    public Weapon getWeapon() {
        return character.getWeapon();
    }

    @Override
    public String getName() {
        return character.getName();
    }

    @Override
    public void setName(String name) {
        character.setName(name);
    }

    @Override
    public void applyStatusEffect(String effect) {
        character.applyStatusEffect(effect);
    }

    @Override
    public void removeStatusEffect(String effect) {
        character.removeStatusEffect(effect);
    }

    @Override
    public void takeDamage(int damage) {
        character.takeDamage(damage);
    }

    @Override
    public void restoreHealth(int amount) {
        character.restoreHealth(amount);
    }

    @Override
    public CloneableCharacter clone() {
        try {
            return (CloneableCharacter) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
