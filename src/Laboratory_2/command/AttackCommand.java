package Laboratory_2.command;

import Laboratory_1.domain.models.character.Character;


import java.util.Random;

public class AttackCommand implements Command {
    private final Character attacker;
    private final Character target;
    private int damageDealt;
    private boolean criticalHit;
    private boolean statusEffectApplied;
    private String statusEffect;

    public AttackCommand(Character attacker, Character target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute() {
        if (attacker.getWeapon() == null) {
            System.out.println(attacker.getName() + " has no weapon equipped and cannot attack.");
            return;
        }

        damageDealt = attacker.attackWithWeapon();

        if (damageDealt > 0) {
            System.out.println(attacker.getName() + " attacks " + target.getName() + " for " + damageDealt + " damage!");
            target.takeDamage(damageDealt);
        }

        applyStatusEffect();
    }


    @Override
    public void undo() {
        System.out.println("Undoing " + attacker.getName() + "'s attack on " + target.getName() + ". Restoring " + damageDealt + " health.");

        if (statusEffectApplied) {
            System.out.println("Removed " + statusEffect + " from " + target.getName());
            target.removeStatusEffect(statusEffect);
        }
    }

//    private int calculateDamage(int baseDamage) {
//        Random rand = new Random();
//
//        double critChance = 0.25;
//        criticalHit = rand.nextDouble() < critChance;
//
//        double missChance = 0.1;
//        if (rand.nextDouble() < missChance) {
//            System.out.println(attacker.getName() + "'s attack missed!");
//            return 0;
//        }
//
//        int finalDamage = baseDamage;
//        if (criticalHit) {
//            finalDamage *= 2;
//            System.out.println("Critical Hit! " + attacker.getName() + " deals extra damage.");
//        }
//
//        return finalDamage;
//    }

    private void applyStatusEffect() {
        Random rand = new Random();
        double effectChance = 0.15;
        statusEffectApplied = rand.nextDouble() < effectChance;

        if (statusEffectApplied) {
            String[] effects = {"bleeding", "stunned", "burned"};
            statusEffect = effects[rand.nextInt(effects.length)];
//            System.out.println(target.getName() + " is now affected by " + statusEffect + "!");
            target.applyStatusEffect(statusEffect);
        }
    }
}



