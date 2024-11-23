package Laboratory_3.decorator;

import Laboratory_1.domain.models.character.Character;

public class EnhancedAttackDecorator extends CharacterDecorator {
    private int extraAttackPower;

    public EnhancedAttackDecorator(Character character, int extraAttackPower) {
        super(character);
        this.extraAttackPower = extraAttackPower;
    }

    @Override
    public void attackWithWeapon() {
        System.out.println(character.getName() + " gains an attack boost of " + extraAttackPower + "!");
        super.attackWithWeapon();
        System.out.println("Enhanced attack performed with " + extraAttackPower + " additional damage!");
    }

}