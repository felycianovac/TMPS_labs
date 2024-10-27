package Laboratory_1.domain.models.weapon;

public interface Weapon {
    void attack();
    void specialAttack();
    void reduceDurability(int amount);
    void repair();
    int getDurability();
    String getName();

}
