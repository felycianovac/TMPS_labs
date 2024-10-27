package Laboratory_1.domain.models.weapon;

public class Staff implements Weapon{
    private int durability = 100;

    @Override
    public void attack() {
        if (durability > 0) {
            System.out.println("Casting a magical bolt with the staff!");
            reduceDurability(8);
        } else {
            System.out.println("The staff is too damaged to use.");
        }
    }

    @Override
    public void specialAttack() {
        if (durability > 20) {
            System.out.println("Unleashing a powerful magical spell!");
            reduceDurability(15);
        } else {
            System.out.println("The staff is too damaged to use a special attack.");
        }
    }

    @Override
    public void reduceDurability(int amount) {
        durability -= amount;
        System.out.println("Staff durability reduced to " + durability);
    }

    @Override
    public void repair() {
        durability = 100;
        System.out.println("Staff has been repaired to full durability.");
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public String getName() {
        return "Staff";
    }
}
