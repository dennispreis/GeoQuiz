package GameManager;

import java.util.HashMap;

public class LevelMap {

    private HashMap<String, Level> map;

    public LevelMap() {
        this.map = new HashMap<>();
        this.map.put("easy", Level.EASY);
        this.map.put("medium", Level.MEDIUM);
        this.map.put("hard", Level.HARD);
        this.map.put("tmp", Level.TMP);
        this.map.put("test", Level.TEST);
    }

    public Level getLevel(String s){
        return this.map.get(s);
    }

}
