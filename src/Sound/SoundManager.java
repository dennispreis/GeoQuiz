package Sound;

import Languages.Language;
import controlP5.ControlP5;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Controller;
import ddf.minim.Minim;
import Main.GeoQuiz;
import sun.security.jca.GetInstance;

import java.util.HashMap;

public class SoundManager {

    private Minim minim;
    private HashMap<SoundName, AudioSample> sounds;
    private AudioPlayer backgroundPlayer;
    private HashMap<Language, HashMap<Integer, AudioSample>> languages;
    private boolean isShowingMenu, isBackgroundPlaying;
    private ControlP5 soundMenu;
    private float volume;

    public SoundManager(Minim minim, ControlP5 soundMenu) {
        this.minim = minim;
        this.soundMenu = soundMenu;
        sounds = new HashMap<>();
        languages = new HashMap<>();
        backgroundPlayer = loadBackgroundSound("background.mp3");
        backgroundPlayer.loop();
        this.isBackgroundPlaying = true;
        this.volume = 60;
    }

    public ControlP5 getSoundMenu() {
        return this.soundMenu;
    }

    public boolean isShowingMenu() {
        return this.isShowingMenu;
    }

    public void showMenu() {
        soundMenu.get("Sound_PlayPause").show();
        soundMenu.get("Sound_Volume").show();
        soundMenu.get("Sound_Language").show();
    }

    public void hideMenu() {
        soundMenu.get("Sound_PlayPause").hide();
        soundMenu.get("Sound_Volume").hide();
        soundMenu.get("Sound_Language").hide();
    }

    public void updateShowMenu() {
        this.isShowingMenu = !isShowingMenu;
    }

    public void setVolume(float vol) {

        if (vol <= 1) muteAll();
        else unmuteAll();

        this.volume = vol;
        setBackgroundVolume(vol);
    }

    private void muteAll() {
        backgroundPlayer.mute();
        sounds.values().forEach(Controller::mute);
    }

    private void unmuteAll() {
        backgroundPlayer.unmute();
        sounds.values().forEach(Controller::unmute);
    }

    public float getVolume() {
        return this.volume;
    }

    private float mapValue(float val, float min1, float max1, float min2, float max2) {
        return ((val - min1) / (max1 - min1) * (max2 - min2) + min2);
    }

    private AudioPlayer loadBackgroundSound(String filename) {
        return minim.loadFile("Sound/Sounds/Background/" + filename);
    }

    private AudioSample loadEventSound(String filename) {
        return minim.loadSample("Sound/Sounds/Events/" + filename);
    }

    //---------EVENT SOUNDS

    public void triggerSound(SoundName name) {
        sounds.get(name).trigger();
    }

    public void loadSounds() {
        sounds.put(SoundName.TEST_SOUND, loadEventSound("testSound.wav"));
    }


    //---------LANGUAGE SOUNDS

    public void loadLanguages() {
        languages.put(Language.GERMAN, new HashMap<>());
        languages.put(Language.ENGLISH, new HashMap<>());
        languages.put(Language.FRENCH, new HashMap<>());
        for (int i = 4; i < 36; i++) {
            try {
                languages.get(Language.GERMAN).put(i, loadGermanLanguageFile(i + ".mp3"));
            } catch (Exception ignore) {
                languages.get(Language.GERMAN).put(i, loadGermanLanguageFile("default.mp3"));
            }

            try {
                languages.get(Language.ENGLISH).put(i, loadEnglishLanguageFile(i + ".mp3"));
            } catch (Exception ignore) {
                languages.get(Language.GERMAN).put(i, loadGermanLanguageFile("default.mp3"));
            }

            try {
                //languages.get(Language.FRENCH).put(i, loadFrenchLanguageFile(i + ".mp3"));
            } catch (Exception ignore) {
                //languages.get(Language.GERMAN).put(i, loadGermanLanguageFile("default.mp3"));
            }

        }
    }

    public void triggerLanguageAudio() {
        int idx = GeoQuiz.getGameManager().getActualQuestion().getId();
        Language language = GeoQuiz.getLanguageManager().getActiveLanguage();

        languages.get(language).get(idx).stop();
        languages.get(language).get(idx).trigger();

    }

    private AudioSample loadGermanLanguageFile(String filename) {
        return minim.loadSample("Sound/Sounds/Questions/german/" + filename);
    }

    private AudioSample loadEnglishLanguageFile(String filename) {
        return minim.loadSample("Sound/Sounds/Questions/english/" + filename);
    }

    private AudioSample loadFrenchLanguageFile(String filename) {
        return minim.loadSample("Sound/Sounds/Questions/french/" + filename);
    }


    //---------BACKGROUND MUSIC

    private void backgroundMusicPause() {
        backgroundPlayer.pause();
    }

    private void backgroundMusicLoop() {
        if (GeoQuiz.getGameManager() == null) {
            backgroundPlayer.loop();
        } else {
            if (!GeoQuiz.getGameManager().isPlaying()) {
                backgroundPlayer.loop();
            }
        }
    }

    public void updateBackgroundPlaying(boolean state) {
        this.isBackgroundPlaying = state;
        if (this.isBackgroundPlaying) backgroundMusicLoop();
        else backgroundMusicPause();
    }

    public boolean isBackgroundPlaying() {
        return this.isBackgroundPlaying;
    }

    private void setBackgroundVolume(float value) {
        value = mapValue(value, 0, 100, -50, 0);
        backgroundPlayer.setGain(value);
    }
}
