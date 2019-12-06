package GameManager;

public enum Category implements GameProperty {
    RIVER("river"),
    MOUNTAIN("mountain"),
    CITIES("cities"),
    WORLD("world"),
    ISLAND("island"),
    LAKE("lake"),
    TEST("test"),
    TMP("tmp");

    private String name;

    Category(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
