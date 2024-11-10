package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Staff;
import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;
import Laboratory_2.observer.GameEvent;
import Laboratory_2.observer.Observer;

import java.util.HashMap;
import java.util.Map;

public class Mage implements Character, CloneableCharacter, Observer {
    private Weapon weapon;
    private String name;
    private int magicPower = 20;
    private int barrierStrength = 6;
    private int health = 100;
    private Map<String, Boolean> statusEffects = new HashMap<>();

    @Override
    public void introduce() {
        System.out.println("I am " + name + ", master of magical arts!");
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

        double critChance = 0.25;
        int baseDamage = 8;
        int damage = baseDamage;

        if (Math.random() < critChance) {
            damage *= 2;
            System.out.println("Critical Magic Burst! " + this.name + " deals " + damage + " magical damage!");
        } else {
            System.out.println(this.name + " casts a spell with " + weapon.getName() + ", dealing " + damage + " damage.");
        }

        double statusEffectChance = 0.2;
        if (Math.random() < statusEffectChance) {
            System.out.println(this.name + " freezes the target with a spell!");
        }

        weapon.reduceDurability(5);
//        System.out.println(weapon.getName() + " durability reduced to " + weapon.getDurability() + ".");
    }

    @Override
    public void defend() {
        int magicBarrierChance = 40;
        boolean barrierActivated = Math.random() * 100 < magicBarrierChance;

        if (barrierActivated) {
            System.out.println(this.name + " conjures a magical barrier to absorb the attack!");
        } else {
            System.out.println(this.name + " fails to defend with magic and takes damage.");
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        if (!(weapon instanceof Staff)) {
            System.out.println(this.name + " can only equip magical staffs. " + weapon.getName() + " is incompatible.");
            return;
        }

        this.weapon = weapon;
        System.out.println(this.name + " equipped a " + weapon.getName() + ".");
    }

    @Override
    public CloneableCharacter clone() {
        try {
            Mage clonedMage = (Mage) super.clone();
            return clonedMage;
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
                magicPower += newLevel * 3;
                barrierStrength += newLevel;
                System.out.println(getName() + " has mastered new spells! Magic Power: " + magicPower + ", Barrier Strength: " + barrierStrength);
            }
            case WORLD_NAME_CHANGE -> {
                System.out.println(getName() + " senses a shift in the arcane energy as the world is now " + event.getData() + ".");
            }
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
