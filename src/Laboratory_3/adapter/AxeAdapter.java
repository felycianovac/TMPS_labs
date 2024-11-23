package Laboratory_3.adapter;

import Laboratory_1.domain.models.weapon.Axe;
import Laboratory_1.domain.models.weapon.Weapon;

public class AxeAdapter implements Weapon {
    private final Axe axe;
    private int stamina = 50;

    public AxeAdapter(Axe axe) {
        this.axe = axe;
    }

    @Override
    public void attack() {
        axe.chop(stamina);
        reduceStamina(15);
    }

    @Override
    public void specialAttack() {
        axe.cleave(stamina);
        reduceStamina(20);
    }

    @Override
    public void reduceDurability(int amount) {
        int currentDurability = axe.getDurability() - amount;
        if (currentDurability <= 0) {
            System.out.println("Axe is broken! Repair required.");
        } else {
            System.out.println("Axe durability reduced by " + amount + ". Remaining durability: " + currentDurability);
        }
    }

    @Override
    public void repair() {
        axe.repairAxe();
    }

    @Override
    public int getDurability() {
        return axe.getDurability();
    }

    @Override
    public String getName() {
        return "Axe";
    }

    private void reduceStamina(int amount) {
        stamina -= amount;
        if (stamina <= 0) {
            System.out.println("Character is exhausted! Stamina must recover before further attacks.");
            stamina = 0;
        } else {
            System.out.println("Stamina reduced by " + amount + ". Remaining stamina: " + stamina);
        }
    }

    public void recoverStamina(int amount) {
        stamina += amount;
        if (stamina > 50) {
            stamina = 50;
        }
        System.out.println("Stamina recovered by " + amount + ". Current stamina: " + stamina);
    }
}
