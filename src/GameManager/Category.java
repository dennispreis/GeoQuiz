package GameManager;

public enum Category implements GameProperty {
    RIVERS("rivers"),
    MOUNTAINS("mountains"),
    CITIES("cities"),
    WORLD("world"),
    ISLANDS("islands"),
    LAKES("lakes"),
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
