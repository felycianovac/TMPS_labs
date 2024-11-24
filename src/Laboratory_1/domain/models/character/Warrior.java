package Laboratory_1.domain.models.character;

import Laboratory_1.domain.models.weapon.Axe;
import Laboratory_1.domain.models.weapon.Sword;
import Laboratory_1.domain.prototype.CloneableCharacter;
import Laboratory_1.domain.models.weapon.Weapon;
import Laboratory_2.observer.GameEvent;
import Laboratory_2.observer.Observer;
import Laboratory_3.adapter.AxeAdapter;

import java.util.HashMap;
import java.util.Map;

public class Warrior implements Character, CloneableCharacter, Observer {
    private Weapon weapon;
    private String name;
    private int attackPower = 15;
    private int shieldStrength = 10;
    private int health = 120;
    private Map<String, Boolean> statusEffects = new HashMap<>();

    @Override
    public void introduce() {
        System.out.println("I am " + name + ", strong and brave!");
    }

    @Override
    public int attackWithWeapon() {
        if (weapon == null) {
            System.out.println(this.name + " has no weapon equipped!");
            return 0;
        }

        if (weapon.getDurability() <= 0) {
            System.out.println(this.name + "'s " + weapon.getName() + " is too damaged to use. Repair it first.");
            return 0;
        }

        double critChance = 0.15;
        int baseDamage = attackPower;
        int damage = baseDamage;

        if (Math.random() < critChance) {
            damage *= 2;
            System.out.println("Powerful Strike! " + this.name + " deals " + damage + " damage!");
        } else {
            System.out.println(this.name + " swings the " + weapon.getName() + ", dealing " + damage + " damage.");
        }

        double statusEffectChance = 0.2;
        if (Math.random() < statusEffectChance) {
            System.out.println(this.name + " stuns the target with a heavy blow!");
            applyStatusEffect("stunned");
        }

        weapon.reduceDurability(8);
        return damage;
    }


    @Override
    public void defend() {
        int blockChance = 60;
        boolean blocked = Math.random() * 100 < blockChance;

        if (blocked) {
            System.out.println(this.name + " blocks the attack with a strong shield!");
            if (weapon instanceof Sword) {
                weapon.reduceDurability(5);
            }
        } else {
            System.out.println(this.name + " couldn't block and takes damage.");
        }
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        if (!((weapon instanceof Sword) || (weapon instanceof AxeAdapter))) {
            System.out.println(this.name + " can only equip swords or special tools. " + weapon.getName() + " is incompatible.");
            return;
        }

        this.weapon = weapon;
        System.out.println(this.name + " equipped a " + weapon.getName() + ".");
    }

    @Override
    public CloneableCharacter clone() {
        try {
            Warrior clonedWarrior = (Warrior) super.clone();
            return clonedWarrior;
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
                attackPower += newLevel * 4;
                shieldStrength += newLevel * 2;
                System.out.println(getName() + " becomes tougher! Attack Power: " + attackPower + ", Shield Strength: " + shieldStrength);
            }
            case WORLD_NAME_CHANGE -> System.out.println(getName() + " feels the challenge of the new " + event.getData() + ".");
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
