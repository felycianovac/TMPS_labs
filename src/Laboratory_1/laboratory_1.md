# Laboratory Work #1 - Creational Design Patterns


## Author: Felicia Novac

----

## Objectives:

* Study and understand the Creational Design Patterns.
* Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
* Use some creational design patterns for object instantiation in a sample project.


## Theory:
In software engineering, **creational design patterns** are design patterns that deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or in added complexity to the design due to inflexibility in the creation procedures. Creational design patterns solve this problem by somehow controlling this object creation. [1]

### Singleton Pattern

The **Singleton pattern** ensures that a class has only one instance and provides a global point of access to this instance. This is especially useful for classes that manage shared resources or configurations, where multiple instances would cause conflicts or unnecessary resource usage.

<p align="center">
  <img src="/src/Laboratory_1/images/singleton.png" alt="Singleton Pattern Structure" width="400"/>
  <br>
  <em>Figure 1. Singleton Pattern Structure</em>
</p>

### Builder Pattern

The **Builder pattern** separates the construction of a complex object from its representation, allowing the same construction process to create different representations. It is ideal for creating objects with multiple optional parts or configurations.

<p align="center">
  <img src="/src/Laboratory_1/images/builder.png" alt="Builder Pattern Structure" width="400"/>
  <br>
  <em>Figure 2. Builder Pattern Structure</em>
</p>

### Prototype Pattern

The **Prototype pattern** is used to create new objects by copying an existing object (the prototype), rather than creating an entirely new instance. This is efficient when the setup cost of an object is high or when similar objects need to be duplicated frequently.


<p align="center">
  <img src="/src/Laboratory_1/images/prototype.png" alt="Prototype Pattern Structure" width="400"/>
  <br>
  <em>Figure 3. Prototype Pattern Structure</em>
</p>

### Object Pooling Pattern

The Object Pooling pattern manages a pool of reusable objects, particularly useful when creating new instances is expensive or when there is a limited set of resources that need to be reused.

<p align="center">
  <img src="/src/Laboratory_1/images/pooling.jpg" alt="Object Pooling Pattern Structure" width="400"/>
  <br>
  <em>Figure 4. Object Pooling Pattern Structure</em>
</p>

### Factory Method

The **Factory Method** pattern provides an interface for creating objects, allowing subclasses to alter the type of object that will be created. Instead of instantiating objects directly, the Factory Method pattern defers instantiation to subclasses, which then decide which specific class to instantiate. This makes it easier to extend and customize object creation in subclasses.

<p align="center">
  <img src="/src/Laboratory_1/images/factorymethod.webp" alt="Factory Method Pattern Structure" width="400"/>
  <br>
  <em>Figure 5. Factory Method Pattern Structure</em>
</p>

### Abstract Factory

The **Abstract Factory** pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. This pattern is useful when a system needs to work with multiple families of related objects but does not need to know the specifics of those objects. By working with interfaces or abstract classes, it’s easier to switch between families of products or extend the application to support new families.

<p align="center">
  <img src="/src/Laboratory_1/images/abstractfactory.png" alt="Abstract Factory Pattern Structure" width="400"/>
  <br>
  <em>Figure 6. Abstract Factory Pattern Structure</em>
</p>

## Domain of the application
I chose the domain of game development because it directly aligns with my area of expertise, where I frequently apply creational design patterns. In games, object creation is complex, with various entities like characters, weapons, and environments needing tailored configurations. Patterns such as Singleton, Factory Method, Abstract Factory, and Prototype efficiently manage these needs—controlling instantiation, ensuring consistency, and optimizing performance when duplicating objects. Simulating these patterns in Java, this project reflects how they support scalable and maintainable object creation in game development.

Overall, the application is a **Game Asset Management System** designed to demonstrate creational design patterns in game development. Its functionalities include:

- Create and Manage Characters
- Equip Characters with Weapons
- Clone Existing Characters
- Track Global Game Settings
- Display and Update Game World



## Used Patterns: 

### 1. Singleton Pattern

In this project, the `GameWorld` class is implemented as a singleton to manage global game settings, such as the difficulty level, ensuring consistent access across the application.

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


The **Abstract Factory** pattern is used in this project to create families of related objects—specifically, characters and their corresponding weapons. This pattern is ideal here because it allows us to create various types of characters (e.g., warriors, mages, archers) along with the correct type of weapon for each character. Using this approach, the game can easily switch between character types without changing the underlying code that interacts with these objects.

**Structure in the Project:**
* **Abstract Factory Interface (`CharacterWeaponFactory`)** defines methods for creating a `Character` and a `Weapon`.
* **Concrete Factories**-each concrete factory (e.g., `WarriorFactory`, `MageFactory`, `ArcherFactory`) implements the `CharacterWeaponFactory` interface to create specific character and weapon types.
* **Abstract Products (`Character` and `Weapon`)** define the behavior expected from any character or weapon.
* **Concrete Products**-specific implementations of characters and weapons, such as `Warrior`, `Mage`, `Sword`, and `Staff`.

### 3. Prototype Pattern:

The **Prototype** pattern is used in this project to create clones of existing characters. This pattern is particularly useful in game development when we need to duplicate complex objects (like characters) with predefined attributes without going through the entire initialization process again. By cloning an object, we can quickly create new instances that retain the original object’s configuration, which is both time-efficient and memory-efficient.

**Structure in the Project:**
* **Prototype Interface (`CloneableCharacter`)** defines the `clone()` method for cloning characters. It extends the `Character` interface, so cloned characters can still interact with other game components as any other character would.
* **Concrete Prototypes**-classes like `Mage`, `Warrior`, and `Archer` implement `CloneableCharacter` and provide cloning capabilities.
* **Cloning Process**-using the `clone()` method, we can easily create duplicates of an existing character while retaining the initial settings, making it easy to produce similar characters with minimal overhead.

## Implementation
This project is organized into specific packages that follow the structure of the creational design patterns used:

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

Figure 7 illustrates the character creation process. The user selects the Warrior class and names the character "Ludus." After the character is created, "Ludus" is introduced as a warrior with the corresponding weapon, "Sword" indicating successful instantiation and weapon assignment.

<p align="center">
  <img src="/src/Laboratory_1/images/create.png" alt="Character Creation with its Respective Tool" width="400"/>
  <br>
  <em>Figure 7. Character Creation with its Respective Tool</em>
</p>

The next figure captures the interaction for managing a character's weapon. The user selects the Warrior's weapon and performs a special attack, reducing the weapon's durability. The messages confirm the special attack action and the resulting durability reduction, showcasing weapon management functionality.

<p align="center">
  <img src="/src/Laboratory_1/images/usetool.png" alt="Tool Usage" width="400"/>
  <br>
  <em>Figure 8. Tool Usage</em>
</p>

Figure 9 demonstrates the functionality of cloning an existing character. The user selects the option to clone, chooses "Ludus" from the available characters, and successfully creates a cloned instance. Both the original and cloned characters are displayed when the list is viewed, showing that they share the same name and type.
<p align="center">
  <img src="/src/Laboratory_1/images/clone.png" alt="Cloning" width="400"/>
  <br>
  <em>Figure 9. Cloning</em>
</p>

Lastly, Figure 10 shows how the user can update the game world's name. By selecting the option to change the name and entering "Lost Village," the game world settings are updated successfully. The new settings are displayed, confirming the name change to "Lost Village" with difficulty level 1.
<p align="center">
  <img src="/src/Laboratory_1/images/modifysettings.png" alt="Changing Game Settings" width="400"/>
  <br>
  <em>Figure 9. Changing Game Settings</em>
</p>

## Conclusions

This laboratory work successfully demonstrated the use of creational design patterns in a game asset management system, providing a structured and efficient approach to object creation, customization, and duplication. By implementing Singleton, Abstract Factory, and Prototype patterns, the project achieved modularity, scalability, and improved resource management. The interactive console application allowed for real-time character creation, weapon assignment, and game world modifications, enhancing the user experience and illustrating the practical advantages of these design patterns in game development.

The use of abstract factory simplified the creation of different character-weapon combinations, while the Prototype pattern enabled efficient cloning of characters. Additionally, the Singleton pattern ensured consistent access to global game settings. Overall, this project underscored the importance of creational design patterns in managing complex object hierarchies and creating flexible, extensible systems that align with real-world application needs.
## References

[1] **Creational pattern** - Accessed October 27, 2024. [https://en.wikipedia.org/wiki/Creational_pattern](https://en.wikipedia.org/wiki/Creational_pattern).
