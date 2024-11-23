package Laboratory_3.decorator;
import Laboratory_1.domain.models.character.Character;

public class DefenseBoostDecorator extends CharacterDecorator {
    private int damageReduction;

    public DefenseBoostDecorator(Character character, int damageReduction) {
        super(character);
        this.damageReduction = damageReduction;
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(0, damage - damageReduction);
        System.out.println(character.getName() + " reduces damage by " + damageReduction + "!");
        super.takeDamage(reducedDamage);
    }

}
