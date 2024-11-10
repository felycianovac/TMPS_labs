# Laboratory Work #2 - Behavioral Design Patterns


## Author: Felicia Novac

----

## Objectives:

*  Study and understand the Behavioral Design Patterns.
* As a continuation of the previous laboratory work, think about what communication between software entities might be involed in your system.
* Implement some additional functionalities using behavioral design patterns.


## Used Design Patterns: 

### Observer Pattern

The **Observer Pattern** is a behavioral design pattern that allows an object (known as the subject) to notify other objects (known as observers) about changes in its state. The observer pattern is also known as the **publish-subscribe** pattern **[1]**.

In simple words, the Observer Pattern is kinda like a "subscribe and notify" system. Think of it like this: the subject (the main object being watched) is like a news channel, and the observers (other objects) are subscribers to that channel. When the news channel has an update, all the subscribers get notified automatically.

Figure 1 illustrates the flow of the this pattern through the UML diagram, where as described before, the Subject maintains a collection of Observers and provides methods to `register`, `unregister`, and `notify` them. When the `notifyObservers()` method is called, it iterates through each Observer in the collection and calls their `update()` method, allowing each observer to independently react to changes in the subject.

<p align="center">
  <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/observer.png" alt="Observer UML Diagram" width="600"/>
  <br>
  <em>Figure 1. Observer Pattern UML Diagram</em>
</p>

In my project, I used the Observer Pattern to manage the relationship between the game world (the game settings) and the characters. The game world holds data like the current level, which impacts each character’s stats. When the game level changes, the world automatically notifies all characters, and each one adjusts its stats accordingly. This keeps characters in sync with the game state without requiring direct links between them.

### Command Pattern

The Command Design Pattern is a behavioral design pattern that turns a request into a stand-alone object called a command **[2]**. To be more specific, imagine having a remote control with buttons that perform different actions. Each button (that will represent the command in our case) knows exactly what it should do when pressed, but the remote itself doesn’t need to know the specifics, so it just triggers the action.

Look at the Figure 2, you can see the UML diagram of the Command Pattern, where the Invoker class triggers commands, the Command interface defines an `execute()` method, and ConcreteCommand classes implement specific actions. The Receiver performs the actual work. This structure allows the invoker to execute commands without knowing their details, making it easy to queue, log, or undo actions by working with command objects.

<p align="center">
  <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/command.webp" alt="Command UML Diagram" width="600"/>
  <br>
  <em>Figure 2. Command Pattern UML Diagram</em>
</p>

Specifically in my project, the Command Pattern organizes character actions (like attacking or defending) as individual command objects. This allows actions to be executed, undone, or replayed independently, providing flexibility for managing character interactions. The Invoker (game menu) triggers commands, while each ConcreteCommand encapsulates specific actions, allowing for clean execution and control over game actions without the game menu needing to know the details.

## Implementation

Before implementing the patterns themselves, I enhanced the character action methods to add complexity, making each character’s abilities unique. Adjustments to methods like `attackWithWeapon()` and `defend()` introduced elements such as critical hits, status effects (like freezing or bleeding), and distinct defense styles.

For example, each character’s attack now includes critical hit logic, optional status effects, and durability reduction:

```java
    public void attackWithWeapon() {
        if (weapon == null) {
            System.out.println(this.name + " has no weapon equipped!");
            return;
        }

        if (weapon.getDurability() <= 0) {
            System.out.println(this.name + "'s " + weapon.getName() + " is too damaged to use. Repair it first.");
            return;
        }

        double critChance = 0.2;
        int baseDamage = attackPower;
        int damage = baseDamage;

        if (Math.random() < critChance) {
            damage *= 2;
            System.out.println("Critical Shot! " + this.name + " deals " + damage + " damage!");
        } else {
            System.out.println(this.name + " fires an arrow with " + weapon.getName() + ", dealing " + damage + " damage.");
        }

        double statusEffectChance = 0.15;
        if (Math.random() < statusEffectChance) {
            System.out.println(this.name + " inflicts bleeding on the target!");
            applyStatusEffect("bleeding");
        }

        weapon.reduceDurability(5);
    }
```

Defense methods are also customized, using probabilities for successful defense and unique responses per character:

```java
    public void defend() {
        int dodgeChance = dodgeSkill * 5;
        boolean dodged = Math.random() * 100 < dodgeChance;

        if (dodged) {
            System.out.println(this.name + " dodges the attack!");
        } else {
            System.out.println(this.name + " couldn't dodge and takes damage.");
        }
    }
```


Now going to the topic, the Observer Pattern implementation succintly described before, includes a `GameWorld.java` class as the subject, which manages characters as observers and notifies them of any state changes. The GameWorld provides method to `add()` observers and `notifyObservers()` method that sends events to all observers. Each character class implements the `Observer.java` interface, defining an `update()` method to specify how they respond to events from GameWorld. Events are encapsulated in the `GameEvent.java` class, which includes an `EventType.java` enum to categorize changes (in my case LEVEL_CHANGE and WORLD_NAME_CHANGE).

Further, I also implemented the Command Pattern, which encapsulates character actions as command objects. This structure allows me to control the actions, allowing each to be executed, undone, or replayed independently
The pattern begins with a `Command.java` interface, which defines two methods `execute()` and undo(). Every specific action, such as `AttackCommand.java`, `DefendCommand.java`, and `SpecialAbilityCommand.java`, implements this interface, allowing these actions to be treated consistently within the game.

For example, `AttackCommand.java` is responsible for encapsulating the logic of an attack action between two characters. The class takes an *attacker* and a *target* as parameters and records the damage dealt to allow for *undo* functionality. In the `execute()` method, AttackCommand calls `attackWithWeapon()` on the attacker, which performs the attack, calculates the damage, and applies it to the target. The `undo()` method respectively reverses the action by restoring the target’s health by the amount of damage dealt, making it possible to roll back the last attack.

```java
    public void execute() {
        if (attacker.getWeapon() == null) {
            System.out.println(attacker.getName() + " has no weapon equipped and cannot attack.");
            return;
        }

        int baseDamage = attacker.getWeapon().getDurability();
        damageDealt = calculateDamage(baseDamage);

        System.out.println(attacker.getName() + " attacks " + target.getName() + " for " + damageDealt + " damage!");
        target.attackWithWeapon();

        applyStatusEffect();
    }

    public void undo() {
        System.out.println("Undoing " + attacker.getName() + "'s attack on " + target.getName() + ". Restoring " + damageDealt + " health.");

        if (statusEffectApplied) {
            System.out.println("Removed " + statusEffect + " from " + target.getName());
            target.removeStatusEffect(statusEffect);
        }
    }
```

Another example is `DefendCommand.java`, which encapsulates defensive actions. When DefendCommand is executed, it calls `defend()` on the character, triggering defense-specific logic, like shield blocking or dodging, based on the character type. DefendCommand is also undoable, though defense actions typically don't require complex undo operations.

To manage commands, a `CommandHistory.java` class stores each executed command in a list. This enables two key features like `undoLastCommand()` and `replayCommands()`. undoLastCommand() allows the game to reverse the most recent action by calling `undo()` on the last command in history, while replayCommands() iterates through all commands in the history and re-executes them, allowing for action sequences to be reviewed or repeated.


P.S. The newly added classes are now stored in the Laboratory_2 folder, which contains behavioral design patterns-specific components. However, some changes have been done in the classes from the Laboratory_1 directory as well, like described earlier.

## Results & Screenshots

Let me now present the program's output results. In Table 1, the *Observer Pattern* is showcased through the automatic responses of characters to changes in the game world. The screenshot on the left demonstrates the Update Game World Name action, where the game world name changes to "Narnia", prompting each character to react uniquely to the new environment. Similar to this, the right screenshot shows the Update Game World Level action. When the difficulty level is increased, each character adjusts their attributes accordingly—Ludus gains additional attack power and shield strength, while Zara increases her magic power and barrier strength.

|                      Update Game World Name                      |                      Update Game World Level                    |
|:-------------------------------------------------------------:|:-----------------------------------------------------------------:|
| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/level.png" > | <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/name.png">|
<p align="center">
  <strong>Table 1.</strong> Observer DP results
</p>

Table 2 on the other hand, illustrates the *Command Pattern* in action, showing various actions being executed, replayed, and undone. In the Attack Action screenshot, Zara attacks Ludus, dealing damage and affecting weapon durability. The Defend Action screenshot shows Ludus blocking an attack with his shield, which reduces his sword’s durability. The Replay All Actions screenshot replays past moves in sequence, giving players a chance to review and analyze their strategies. Finally, the Undo Last Action screenshot shows Ludus’s last attack on Zara being undone, restoring her health and showcasing the Command Pattern's flexibility in allowing players to reverse actions. Overall, these actions are primarily executed through implementations of the `Command.java` interface, which defines the structure for each command in the game. 


|                      Attack Action                      |                      Defend Action                    |                      Replay All Actions                    |                      Undo Last Action                    |
|:-------------------------------------------------------------:|:-----------------------------------------------------------------:|:-----------------------------------------------------------------:|:-----------------------------------------------------------------:|
| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/attack.png" > | <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/defend.png">| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/replay.png">| <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_2/images/undo.png">|
<p align="center">
  <strong>Table 2.</strong> Command DP results
</p>

## Conclusions

In conclusion, this lab was a fun and valuable experience. Working with the **Observer** and **Command** Patterns added flexibility and structure to the game simulation, making character actions and world changes feel more dynamic. These patterns made the code cleaner and the gameplay more interactive, helping me see how design patterns can really improve a project.

## References
[1] **The Observer Pattern** - Accessed November 10, 2024. [https://medium.com/@satyendra.jaiswal/unlocking-the-power-of-software-design-a-deep-dive-into-behavioral-patterns-observer-pattern-b5e8fe2d7ac7#:~:text=The%20Observer%20Pattern%20is%20a,as%20the%20publish%2Dsubscribe%20pattern.](https://medium.com/@satyendra.jaiswal/unlocking-the-power-of-software-design-a-deep-dive-into-behavioral-patterns-observer-pattern-b5e8fe2d7ac7#:~:text=The%20Observer%20Pattern%20is%20a,as%20the%20publish%2Dsubscribe%20pattern.).

[2] **The Command Pattern** - Accessed November 10, 2024. [https://www.geeksforgeeks.org/command-pattern/](https://www.geeksforgeeks.org/command-pattern/).
