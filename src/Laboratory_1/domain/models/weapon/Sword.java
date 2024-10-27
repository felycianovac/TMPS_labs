package Laboratory_1.domain.models.weapon;

public class Sword implements Weapon{
    private int durability = 100;

    @Override
    public void attack() {
        if (durability > 0) {
            System.out.println("Swinging a mighty sword!");
            reduceDurability(10);
        } else {
            System.out.println("The sword is too damaged to use.");
        }
    }

    @Override
    public void specialAttack() {
        if (durability > 20) {
            System.out.println("Unleashing a powerful sword slash!");
            reduceDurability(20);
        } else {
            System.out.println("The sword is too damaged to use a special attack.");
        }
    }

    @Override
    public void reduceDurability(int amount) {
        durability -= amount;
        System.out.println("Sword durability reduced to " + durability);
    }

    @Override
    public void repair() {
        durability = 100;
        System.out.println("Sword has been repaired to full durability.");
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public String getName() {
        return "Sword";
    }
}
