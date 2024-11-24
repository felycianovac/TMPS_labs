
# Laboratory Work #3 - Structural Design Patterns


## Author: Felicia Novac

----

## Objectives:

* Study and understand the Structural Design Patterns.
* As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.
* Implement some additional functionalities using structural design patterns.

## Used Design Patterns: 

To begin with, structural design patterns focus on how objects and classes are composed to form larger structures. These patterns simplify interactions, allow for more flexible designs, and improve maintainability.

### Adapter Pattern

The **Adapter Pattern** is a structural design pattern that allows objects with incompatible interfaces to collaborate **[1]**. In simpler terms, it's like a translator that ensures two different systems can understand each other without modifying their existing structures. 

I used it to add an extra tool for characters, the `Axe`, which didn’t initially fit the `Weapon` interface. The `AxeAdapter` acts as a bridge, translating the Weapon interface methods into the Axe’s specific methods, making the Axe fully compatible and usable within the system.

### Decorator Pattern

The **Decorator Pattern** is another structural design pattern hat lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors **[2]**. In simpler terms, it’s like adding layers of extra features to an object without changing its core.

I used the Decorator Pattern to enhance character abilities by applying *buffs* such as *increased attack power* or *enhanced defense*. For example, the `EnhancedAttackDecorator` adds bonus attack damage, while the `DefenseBoostDecorator` reduces incoming damage. These decorators wrap around existing character objects, extending their behavior without altering the original character class.

### Composite Pattern

The **Composite Pattern** is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects **[3]**.  It’s like organizing individual items into groups, so you can perform actions on both single items and the whole group seamlessly.

I used the Composite Pattern to manage groups of characters in my project. The `CharacterGroup` class represents a collection of characters that can perform actions like attacking or defending as a single unit. For example, a group of warriors and mages can execute a combined attack, with the same command working for both individual characters and entire groups.

### Facade Pattern

And the last one, the **Facade Pattern** is a structural design pattern that provides a simplified interface to a library, a framework, or any other complex set of classes **[4]**. It ensures a single point of access to multiple underlying functionalities, making the system easier to use and reducing dependency on internal details.

In my project, the `GameMenu` class acts as a facade, developed since lab 1. It centralizes all operations, such as creating characters, managing actions, and interacting with game settings providing a user-friendly interface for the entire system. However, as this implementation has been present from the beginning of the labs, I won’t focus on it in this report.

## Implementation

Now let's dive deeper into the implementation of each pattern separately. Starting with the **Adapter**, as previously mentioned, it is used to integrate the newly added tool, the **Axe**, into the system besides its incompatibility with the Weapon interface. We want the `Axe` to be accessible in the same way as other weapons for the client, through the `Weapon` interface, right?

So, basically what we have to do is create an adapter class that implements the Weapon interface but uses an Axe object internally, for this reason I've created the `AxeAdapter` class. From now on,  when the client will call methods like `attack()`, `repair()` or whatever on the Weapon interface, the AxeAdapter will intercept these calls and translate them into the Axe's own methods like `chop()`, `repairAxe()` and so on. This approach will allow the Axe to function just like any other weapon in the game, while still keeping its unique behavior (like handling stamina and using axe-specific attacks). 

For better understanding, let me present the UML diagram of the Axe Adapter implementation within the project **[Fig 1]**. The Client interacts directly with the Weapon interface, unaware of whether the underlying implementation is a Sword, Bow, or, in this case, the AxeAdapter. This encapsulation ensures that the addition of the Axe does not disrupt the existing system or require changes in the client code.

<p align="center">
  <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/Axe Adapter.jpg" alt="Adapter UML Diagram" width="600"/>
  <br>
  <em>Figure 1. Adapter Pattern Implementation UML Diagram</em>
</p>

Also, to illustrate how the transition between the Axe class and the Weapon interface works, let’s take a closer look at how the `attack()` method in the Weapon interface is translated into the `chop()` method of the Axe class. 


```java
public interface Weapon {
    void attack();
}

public class Axe {
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
}

public class AxeAdapter implements Weapon {
    private final Axe axe;

    public AxeAdapter(Axe axe) {
        this.axe = axe;
    }

    @Override
    public void attack() {
        axe.chop(stamina);
        reduceStamina(15);
    }
}
```

Next, the `Decorator Pattern`, as mentioned earlier, is used to dynamically add new behaviors to objects without altering their structure. In the context of this project, I used the Decorator Pattern to enhance character abilities by applying buffs, such as increased attack power or enhanced defense. This was achieved through specialized decorators that wrap around the base character objects.

The idea here is simple, the client interacts with a decorated character just like a normal character. However, the decorator modifies or extends the behavior of specific methods, such as attacking or taking damage, based on the additional functionality it provides.

For example, let’s take the case of the `EnhancedAttackDecorator`. This decorator adds bonus damage to the character's attacks, providing a temporary buff to increase their combat effectiveness

```java
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
```
The `attackWithWeapon()` method is overridden to add extra attack power. It first outputs a message about the buff, calls the base character’s attack method, and then appends an additional message indicating the extra damage dealt. Similarly, the `DefenseBoostDecorator` modifies the `takeDamage()` method to reduce incoming damage, applying a defensive buff before invoking the base character's damage-handling logic. Both decorators rely on the `CharacterDecorator`, which serves as a wrapper by implementing the Character interface.

For a better understanding of how decorators fit into the system, here’s the UML diagram illustrating the Decorator Pattern implementation **[Fig 2]**.

<p align="center">
  <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/Character Decorator.jpg" alt="Decorator UML Diagram" width="600"/>
  <br>
  <em>Figure 2. Decorator Pattern Implementation UML Diagram</em>
</p>

The third pattern, **Composite** is implemented using the `CharacterGroup` class. The CharacterGroup acts as a container that can hold multiple `Character` objects, whether they are *Mage*, *Warrior*, *Archer*, or even other CharacterGroup instances. The client interacts with the group in the same way it interacts with an individual character, invoking methods like `attackWithWeapon()` or `defend()`. Internally, the CharacterGroup propagates these method calls to all its members, allowing for collective actions. For example, a group attack command issued to a CharacterGroup results in all characters within the group executing their individual attack methods.

Here’s the UML diagram **[Fig 3]** illustrating the Composite Pattern implementation. The leaf nodes in this figure are the individual character classes (Mage, Warrior, and Archer) and the composite is the CharacterGroup, with all of them implementing the base Character interface.

<p align="center">
  <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/Character Composite.jpg" alt="Composite UML Diagram" width="600"/>
  <br>
  <em>Figure 3. Composite Pattern Implementation UML Diagram</em>
</p>

## Results & Screenshots

To evaluate the functionality and demonstrate the execution of the newly added game mechanics, I have included several screenshots representing key moments in gameplay.

The screenshots provided in Table 1, showcase the Adapter Pattern in action. The first screen shows the process of equipping a special tool, the Axe, ensuring compatibility with the Weapon interface. The second screenshot highlights the Axe being used in combat, showcasing its seamless integration into the system.

|                      Equip Special Tool                      |                      Attack With Special Tool                    |
|:-------------------------------------------------------------:|:-----------------------------------------------------------------:|
| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/equipspecial.png" > | <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/attackspecial.png">|
<p align="center">
  <strong>Table 1.</strong> Adapter Pattern Screenshots
</p>

Next, have a look at the second table, where the first image illustrates the application of a temporary attack boost to a character using the Decorator Pattern. The column outlines the character performing an enhanced attack, reflecting the additional damage dynamically applied by the decorator.

|                      Buffing                      |                      Attack After Buff                    |
|:-------------------------------------------------------------:|:-----------------------------------------------------------------:|
| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/buffing.png" > | <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/attack_after_buff.png">|
<p align="center">
  <strong>Table 2.</strong> Decorator Pattern Screenshots
</p>

And last but not least, the third table outlines the group management as it follows:  the first image captures the creation of a new group, enabling collective management of multiple characters. The second image shows the addition of characters to the group, forming a composite structure. The third image demonstrates a coordinated group attack, where all members contribute to the total damage output.

Creating Character Group                  |Joining Character Group                 |Group Attack           |
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/create_group.png)  |  ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/join_group.png) | ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_3/images/group_attack.png)  | 
<p align="center">
  <strong>Table 3.</strong> Composite Pattern Screenshots
</p>



## Conclusions

Overall, the structural design patterns have greatly improved the system’s functionality and organization. The Adapter Pattern ensured compatibility for new tools like the Axe, while the Decorator Pattern allowed dynamic enhancements such as buffs. The Composite Pattern streamlined group management, making collective actions efficient. These patterns together have made the system more cohesive, flexible, and maintainable.

## References
[1] **The Adapter Pattern** - Accessed November 24, 2024. [https://refactoring.guru/design-patterns/adapter](https://refactoring.guru/design-patterns/adapter).

[2] **The Decorator Pattern** - Accessed November 24, 2024. [https://refactoring.guru/design-patterns/decorator](https://refactoring.guru/design-patterns/decorator).

[3] **The Composite Pattern** - Accessed November 24, 2024. [https://refactoring.guru/design-patterns/composite](https://refactoring.guru/design-patterns/composite).

[4] **The Facade Pattern** - Accessed November 24, 2024. [https://refactoring.guru/design-patterns/facade](https://refactoring.guru/design-patterns/facade).
