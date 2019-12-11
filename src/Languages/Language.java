package Languages;

public enum Language {
    GERMAN("german"),
    ENGLISH("english"),
    FRENCH("french");

    private String name;

    Language(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
