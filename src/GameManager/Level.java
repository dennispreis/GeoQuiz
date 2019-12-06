package GameManager;

public enum Level implements GameProperty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    TEST("test"),
    TMP("tmp");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
