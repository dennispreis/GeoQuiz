package Languages;

public enum Language {
    GERMAN("german"),
    ENGLISH("english");

    private String name;

    Language(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
