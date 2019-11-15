package GameManager;

public class GameManager {

    private Category category;
    private Level level;

    public GameManager(){
        this.category = Category.MOUNTAINS;
        this.level = Level.EASY;
    }

    public void increaseLevel(){
    }

    public void decreaseLevel(){
    }

    public void increaseCategory(){

    }

    public void decreaseCategory(){

    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
