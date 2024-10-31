# Laboratory Work #1 - Creational Design Patterns


## Author: Felicia Novac

----

## Objectives:

* Study and understand the Creational Design Patterns.
* Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
* Use some creational design patterns for object instantiation in a sample project.


## Theory:
In software engineering, **creational design patterns** are design patterns that deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or in added complexity to the design due to inflexibility in the creation procedures. Creational design patterns solve this problem by somehow controlling this object creation. **[1]**

### Singleton, Abstract Factory & Prototype Patterns

Let's first focus on the **Singleton**, **Abstract Factory** and **Prototype** patterns, such that these three were implemented within my project.

The **Singleton** pattern, in simple words, ensures that there’s only one instance of a class and provides a global way to access it. This is useful for shared resources or configurations that need to stay consistent. You can see how this works in Table 1's first column, where the Singleton's UML Diagram is presented, and it shows that the `getInstance()` method creates and returns a single shared instance to be used by the `Client`.

The **Abstract Factory** pattern on the other hand, provides an interface for creating families of related objects without needing to know their exact classes. This is useful in situations where an application needs to work with different sets of related objects but doesn’t need to know the specifics. It's core logic can also be seen in Table 1, second column, where the UML diagram for Abstract Factory is presented. Here, the `AbstractFactory` defines methods to create related products. Each `ConcreteFactory` produces specific product families, allowing the `Client` to create these products without knowing their exact types, making it easy to switch between product families.

The third one, **Prototype** pattern allows objects to be created by copying an existing instance, called a prototype. So, instead of creating new instances from scratch, we can simply create a prototype. It's structure is also presented in Table 1's third column, which shows how the Client uses a Prototype interface to clone ConcretePrototypes, creating duplicates without knowing their exact type.


Singleton                  |Abstract Factory                 |Prototype           |
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/singleton.png)  |  ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/abstractfactory.png) | ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/prototype.png)  | 
<p align="center">
  <strong>Table 1.</strong> Singleton, Abstract Factory & Prototype's UML diagrams
</p>

### Builder, Object Pooling & Factory Method patterns

I'll be focusing less on **Builder**, **Object Pooling** and **Factory Method** patterns, but still let's traverse them.

The **Builder** pattern constructs complex objects step-by-step, useful when an object requires multiple configurations. Look at the Table 2's first column, there, the `Director` coordinates the `Builder` in creating a `Product`, where each `ConcreteBuilder` completes specific parts, allowing for flexibility in object configurations.

The **Object** Pooling pattern reuses objects from a pool instead of creating new ones, improving performance when instantiating objects is costly. In the Table 2's third column, the `Client` accesses the `ObjectPool` to get instances from `ReusablePool`, supporting efficient memory and resource management by reusing objects instead of recreating them.

The **Factory Method** pattern provides an interface to create objects, allowing subclasses to decide the exact type, enhancing flexibility in object creation. Have also a look at the Table 2's second column, in the diagram, `Creator` provides a factoryMethod, and `ConcreteCreator` uses it to produce instances of `ConcreteProduct`, aligning with our explanation on flexible and dynamic object creation.


| Builder                  | Factory Method                 | Object Pooling           |
|:-------------------------:|:-------------------------:|:-------------------------:|
| ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/builder.png)  |  ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/factorymethod.webp) | <img src="https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/pooling.jpg" width="400"/> |
<p align="center">
  <strong>Table 2.</strong> Builder, Object Pooling & Factory Method's UML diagrams
</p>


## Domain of the application
I chose game area for this project because it aligns with my job and is an area where creational design patterns are especially useful in my opinion. In games, creating objects like characters, weapons, and environments requires specific configurations and often complex setups. Patterns like Singleton, Abstract Factory, and Prototype help manage this complexity by controlling object creation, maintaining consistency, and improving performance when duplicating objects. I haven't really created a game, but at least I tried to simulate it's logic "behind the scenes" in Java, to show their applicability.

Overall, the project is a **Game Asset Management System** designed to demonstrate creational design patterns in game development. Its functionalities include:

- Create and Manage Characters
- Equip Characters with Weapons
- Clone Existing Characters
- Track Global Game Settings
- Display and Update Game World

## Used Patterns: 

### 1. Singleton Pattern

In this project, the `GameWorld` class is implemented as a singleton to manage global game settings, such as the difficulty level and the game world's name, ensuring consistent access across the application.

```java
public class GameWorld {
    private static GameWorld instance;
    private String name;
    private int level;

    // Private constructor to prevent instantiation from outside
    private GameWorld() {
        this.name = "Fantasy World";
        this.level = 1;
    }

    // Static method to provide access to the single instance
    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }


    public void displaySettings() {
        System.out.println("World: " + name + ", Difficulty Level: " + level);
    }
}
```
### 2. Abstract Factory Pattern

The **Abstract Factory** pattern is used in this project to create families of related objects—specifically, characters and their corresponding weapons. This pattern is ideal here because it allows me to create various types of characters (e.g., warriors, mages, archers) along with the correct type of weapon for each character. It also means I can switch character types or add new ones without needing to change the core game code that works with these objects.

Especially, the Abstract Factory Interface `CharacterWeaponFactory` defines methods for creating both a Character and a Weapon. Each Concrete Factory, such as `WarriorFactory`, `MageFactory`, and `ArcherFactory` implements this interface to create specific types of characters and their respective weapons. The Abstract Products `Character` and `Weapon` set the expected behaviors for any character or weapon. Finally, the Concrete Products include specific implementations like `Warrior`, `Mage`, `Sword`, and `Staff`, providing the actual character and weapon types used in the game.

### 3. Prototype Pattern:

I've used the **Prototype** in this "game" to create clones of existing characters. I find this pattern really handy in game development when I need to duplicate complex objects, like characters, with all their predefined attributes. Instead of reinitializing everything from scratch, I can simply clone an object to quickly create a new instance that keeps the original’s settings, saving both time and memory.

Specifically, the Prototype Interface `CloneableCharacter` defines the `clone()` method used for cloning characters. It extends the `Character` interface, allowing cloned characters to interact with other game components just like any other character. Concrete Prototypes, such as `Mage`, `Warrior`, and `Archer`, implement `CloneableCharacter` and offer cloning capabilities. Through the Cloning Process with the `clone()` method, we can quickly create duplicates of an existing character, keeping the initial settings intact and allowing for efficient creation of similar characters with minimal overhead.

## Implementation
I've organized this project into directories according to the creational design patterns used:

```
Laboratory_1/
├── app/
│   ├── GameMenu.java
├── client/
│   ├── Main.java
├── domain/
│   ├── factory/
│   │   ├── ArcherFactory.java
│   │   ├── CharacterWeaponFactory.java
│   │   ├── MageFactory.java
│   │   └── WarriorFactory.java
│   ├── models/
│   │   ├── character/
│   │   │   ├── Character.java            
│   │   │   ├── Warrior.java             
│   │   │   ├── Mage.java                 
│   │   │   └── Archer.java              
│   │   └── weapon/
│   │       ├── Weapon.java               
│   │       ├── Sword.java                
│   │       ├── Staff.java               
│   │       └── Bow.java       
│   ├── prototype/
│   │   ├── CloneableCharacter.java
└─  └──singleton/
        └──  GameWorld.java
```

### Class Descriptions
1. The `GameMenu.java` class acts as a **Facade** to manage user interactions. It provides options for creating characters, equipping weapons, cloning characters, and viewing or updating game settings. It uses different factory classes to instantiate characters and weapons without exposing complex creation logic to the client.

2. The `Main.java` is the entry point of the application. It initializes the `GameMenu` class and starts the interactive console menu, facilitating user choices for various game functions.

3. The `CharacterWeaponFactory.java` interface declares methods for creating `Character` and `Weapon` objects. This interface abstracts the creation process, allowing concrete factories to define specific character-weapon pairs.

4. The `ArcherFactory.java`, `MageFactory.java` and `WarriorFactory.java`  implement `CharacterWeaponFactory` to create a specific character type and its matching weapon:
```java
public class WarriorFactory implements CharacterWeaponFactory {
  @Override
  public Character createCharacter() {
    return new Warrior();
  }

  @Override
  public Weapon createWeapon() {
    return new Sword();
  }
}
```
5. The `Character.java` is an abstract **Product** interface that defines common behavior for all characters, such as `introduce()` and other shared methods. It serves as a base for all character types.

6. The `Warrior.java`, `Mage.java` and `Arcjer.java` are concrete implementations of `Character` representing different character types in the game. Each class implements the `introduce()` method uniquely to describe its characteristics.
```java
public class Mage implements Character {
  public void introduce() {
    System.out.println("I am a Mage, master of magical arts!");
  }
}
```
7. The `Weapon.java` is an abstract **Product** interface that defines behaviors for all weapon types, such as `specialAttack()` or `attack()`. It serves as a base interface for concrete weapon classes.

8. The `Sword.java`, `Staff.java`, `Bow.java` are concrete implementations of the `Weapon` interface. 
```java
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
```
9. The `CloneableCharacter.java` is an interface that extends `Character` to add cloning functionality, implementing the **Prototype** pattern. It declares a `clone()` method, allowing character instances to be duplicated.
```java
public interface CloneableCharacter extends Character, Cloneable{
    CloneableCharacter clone();
}
```

10. The `GameWorld.java` class implements the **Singleton** pattern, ensuring that only one instance of `GameWorld` exists throughout the application. It also manages game-wide settings, such as `level`, which can be set and retrieved from anywhere in the game.


## Results

The table below summarizes key interactions in the game:

| Character Creation                  | Tool Usage                | Cloning           |Changing Game Setting          |
|:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|
| ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/create.png)  |  ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/usetool.png) | ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/clone.png) | ![](https://github.com/felycianovac/TMPS_labs/blob/main/src/Laboratory_1/images/modifysettings.png) |
<p align="center">
  <strong>Table 3.</strong> Results
</p>

The **Character Creation** column describes the process where I created a character by choosing the Warrior class and naming him "Ludus." The character was successfully introduced as a warrior with the assigned weapon, "Sword," confirming that character creation and weapon assignment were successful.

In the **Tool Usage** column, I tested weapon functionality by selecting Ludus’s sword and performing a special attack. This action reduced the weapon's durability, with console messages confirming the special attack and showing the updated durability.

The **Cloning** column demonstrates the cloning feature. I chose to clone "Ludus," successfully creating a duplicate with the same name and type. Viewing the list displays both the original "Ludus" and his clone, confirming the clone feature works as intended.

Lastly, the **Changing Game Settings** column captures the game world update process. I selected the option to rename the world to "Lost Village." The new name, along with the difficulty level, was displayed, verifying that the game world settings were successfully updated.

## Conclusions

Overall, this laboratory work gave me hands-on experience with using creational design patterns to simplify game development. The Singleton pattern kept my game settings consistent, while the Abstract Factory made creating character-weapon pairs straightforward. The Prototype pattern let me easily clone characters, saving time and resources. Together, these patterns helped me build a scalable and efficient game simulation.

**[1]** **Creational pattern** - Accessed October 27, 2024. [https://en.wikipedia.org/wiki/Creational_pattern](https://en.wikipedia.org/wiki/Creational_pattern).
