package GameManager;

import java.util.HashMap;

public class CategoryMap {

    private HashMap<String, Category> map;

    public CategoryMap(){
        this.map = new HashMap<>();
        this.map.put("cities", Category.CITIES);
        this.map.put("mountains", Category.MOUNTAIN);
        this.map.put("islands", Category.ISLAND);
        this.map.put("lakes", Category.LAKE);
        this.map.put("rivers", Category.RIVER);
        this.map.put("world", Category.WORLD);
        this.map.put("tmp", Category.TMP);
        this.map.put("test", Category.TEST);
    }

    public Category getCategory(String s){
        return map.get(s);
    }
}
