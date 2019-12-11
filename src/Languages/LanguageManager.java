package Languages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    private ResourceBundle myResources;
    private ResourceBundle myQuestions;
    private HashMap<Language, Locale> locals;
    private Language activeLanguage;

    public LanguageManager() {
        locals = new HashMap<>();
        loadLocalsIntoMap();
        activeLanguage = Language.ENGLISH;
        myResources = getBundle(activeLanguage);
        myQuestions = getQuestionBundle(activeLanguage);
    }

    private void loadLocalsIntoMap() {
        this.locals.put(Language.ENGLISH, new Locale("en", "EN"));
        this.locals.put(Language.GERMAN, new Locale("de", "DE"));
        this.locals.put(Language.FRENCH, new Locale("fr", "FR"));
    }

    public String getString(String str) {
        return this.myResources.getString(str);
    }

    public String getQuestionsString(String str) {
        return this.myQuestions.getString(str);
    }

    public Language getActiveLanguage() {
        return this.activeLanguage;
    }

    private ResourceBundle getBundle(Language activeLanguage) {
        return ResourceBundle.getBundle("Languages/Bundle", this.locals.get(activeLanguage));
    }

    private ResourceBundle getQuestionBundle(Language activeLanguage) {
        return ResourceBundle.getBundle("Languages/Questions", this.locals.get(activeLanguage));
    }

    public void setActiveLanguage(String languageName) {
        Arrays.stream(Language.values()).filter(language -> language.getName().equals(languageName)).forEach(language -> {
            this.activeLanguage = language;
            myResources = getBundle(this.activeLanguage);
            myQuestions = getQuestionBundle(this.activeLanguage);
        });
    }
}
