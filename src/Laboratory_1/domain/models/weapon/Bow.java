package Laboratory_1.domain.models.weapon;

public class Bow implements Weapon{
    private int durability = 100;

    @Override
    public void attack() {
        if (durability > 0) {
            System.out.println("Shooting an arrow with the bow!");
            reduceDurability(5);
        } else {
            System.out.println("The bow is too damaged to use.");
        }
    }

    @Override
    public void specialAttack() {
        if (durability > 20) {
            System.out.println("Shooting a rain of arrows!");
            reduceDurability(15);
        } else {
            System.out.println("The bow is too damaged to use a special attack.");
        }
    }

    @Override
    public void reduceDurability(int amount) {
        durability -= amount;
        System.out.println("Bow durability reduced to " + durability);
    }

    @Override
    public void repair() {
        durability = 100;
        System.out.println("Bow has been repaired to full durability.");
    }

    @Override
    public int getDurability() {
        return durability;
    }
}
