package Sound;

import controlP5.ControlP5;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Controller;
import ddf.minim.Minim;
import Main.GeoQuiz;

import java.util.HashMap;

public class SoundManager {

    private Minim minim;
    private HashMap<SoundName, AudioSample> sounds;
    private AudioPlayer backgroundPlayer;
    private boolean isShowingMenu, isBackgroundPlaying;
    private ControlP5 soundMenu;
    private float volume;

    public SoundManager(Minim minim, ControlP5 soundMenu) {
        this.minim = minim;
        this.soundMenu = soundMenu;
        sounds = new HashMap<>();
        loadSounds();
        backgroundPlayer = loadBackgroundSound("background.mp3");
        //backgroundPlayer.loop();
        this.isBackgroundPlaying = true;
        this.volume = 60;
    }

    private void loadSounds() {
        sounds.put(SoundName.TEST_SOUND, loadEventSound("testSound.wav"));
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

    public void triggerSound(SoundName name){
        sounds.get(name).trigger();
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