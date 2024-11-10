package Laboratory_1.domain.singleton;

import Laboratory_2.observer.EventType;
import Laboratory_2.observer.GameEvent;
import Laboratory_2.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private static GameWorld instance;
    private List<Observer> observers = new ArrayList<>();

    private String name;
    private int level;

    private GameWorld() {
        this.name = "Fantasy World";
        this.level = 1;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyObservers(new GameEvent(EventType.LEVEL_CHANGE, level));

    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers(new GameEvent(EventType.WORLD_NAME_CHANGE, name));

    }



    public void displaySettings() {
        System.out.println("World: " + name + ", Difficulty Level: " + level);
    }

    private void notifyObservers(GameEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
