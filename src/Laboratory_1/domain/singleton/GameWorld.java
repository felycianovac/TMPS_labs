package Laboratory_1.domain.singleton;
public class GameWorld {
    private static GameWorld instance;
    private String name;
    private int level;

    private GameWorld() {
        this.name = "Fantasy World";
        this.level = 1;
    }

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
