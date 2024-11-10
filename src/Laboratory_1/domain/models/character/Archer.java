package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Bow;
import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;
import Laboratory_2.observer.GameEvent;
import Laboratory_2.observer.Observer;

import java.util.HashMap;
import java.util.Map;

public class Archer implements Character, CloneableCharacter, Observer {
    private Weapon weapon;
    private String name;
    private int attackPower = 10;
    private int dodgeSkill = 8;
    private int health = 90;
    private Map<String, Boolean> statusEffects = new HashMap<>();

    @Override
    public void introduce() {
        System.out.println("I am " + name + ", precise and deadly from a distance!");
    }

    @Override
    public void attackWithWeapon() {
        if (weapon == null) {
            System.out.println(this.name + " has no weapon equipped!");
            return;
        }

        if (weapon.getDurability() <= 0) {
            System.out.println(this.name + "'s " + weapon.getName() + " is too damaged to use. Repair it first.");
            return;
        }

        double critChance = 0.2;
        int baseDamage = attackPower;
        int damage = baseDamage;

        if (Math.random() < critChance) {
            damage *= 2;
            System.out.println("Critical Shot! " + this.name + " deals " + damage + " damage!");
        } else {
            System.out.println(this.name + " fires an arrow with " + weapon.getName() + ", dealing " + damage + " damage.");
        }

        double statusEffectChance = 0.15;
        if (Math.random() < statusEffectChance) {
            System.out.println(this.name + " inflicts bleeding on the target!");
            applyStatusEffect("bleeding");
        }

        weapon.reduceDurability(5);
    }

    @Override
    public void defend() {
        int dodgeChance = dodgeSkill * 5;
        boolean dodged = Math.random() * 100 < dodgeChance;

        if (dodged) {
            System.out.println(this.name + " dodges the attack!");
        } else {
            System.out.println(this.name + " couldn't dodge and takes damage.");
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        if (!(weapon instanceof Bow)) {
            System.out.println(this.name + " can only equip bows. " + weapon.getName() + " is incompatible.");
            return;
        }

        this.weapon = weapon;
        System.out.println(this.name + " equipped a " + weapon.getName() + ".");
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

    @Override
    public void update(GameEvent event) {
        switch (event.getType()) {
            case LEVEL_CHANGE -> {
                int newLevel = (int) event.getData();
                attackPower += newLevel * 2;
                dodgeSkill += newLevel;
                System.out.println(getName() + " becomes swifter! Attack Power: " + attackPower + ", Dodge Skill: " + dodgeSkill);
            }
            case WORLD_NAME_CHANGE -> System.out.println(getName() + " notes the world has changed to " + event.getData() + ".");
        }
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " now has " + health + " health.");
    }

    @Override
    public void restoreHealth(int amount) {
        health += amount;
        System.out.println(name + " now has " + health + " health.");
    }

    @Override
    public void applyStatusEffect(String effect) {
        statusEffects.put(effect, true);
        System.out.println(name + " is now affected by " + effect + ".");
    }

    @Override
    public void removeStatusEffect(String effect) {
        if (statusEffects.containsKey(effect) && statusEffects.get(effect)) {
            statusEffects.put(effect, false);
            System.out.println(effect + " has been removed from " + name + ".");
        }
    }
}
