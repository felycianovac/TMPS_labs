package Laboratory_1.domain.models.weapon;

import java.util.Random;

public class Axe {
    private int durability = 50;
    private int staminaRequired = 15;
    private final Random random = new Random();

    public boolean canPerformMove(int stamina) {
        return stamina >= staminaRequired;
    }

    public void chop(int stamina) {
        if (!canPerformMove(stamina)) {
            System.out.println("Not enough stamina to chop with the axe!");
            return;
        }
        if (durability > 0) {
            durability -= 10;
            if (isCriticalHit()) {
                System.out.println("Axe performs a powerful critical chop!");
            } else {
                System.out.println("Axe performs a standard chop.");
            }
        } else {
            System.out.println("Axe is too damaged to use!");
        }
    }

    public void cleave(int stamina) {
        if (!canPerformMove(stamina)) {
            System.out.println("Not enough stamina to perform a cleave!");
            return;
        }
        if (durability >= 20) {
            durability -= 20;
            System.out.println("Axe performs a devastating cleave, striking multiple enemies!");
        } else {
            System.out.println("Axe is too damaged to perform a cleave.");
        }
    }

    public void repairAxe() {
        durability = 100;
        System.out.println("Axe repaired to full durability.");
    }

    public int getDurability() {
        return durability;
    }

    private boolean isCriticalHit() {
        return random.nextInt(100) < 30;
    }
}

