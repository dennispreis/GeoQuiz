/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Images;

import static Images.ImageName.*;

import java.util.HashMap;
import java.util.Map;

import processing.core.*;

/**
 * @author Tunjing
 */
public class ImageMap {

    private static PApplet applet;
    private static Map<String, ImageName> imageMap = new HashMap<>();
    private static Map<ImageName, PImage> images = new HashMap<>();


    public ImageMap(PApplet applet) {
        ImageMap.applet = applet;
        images = loadImages();
        imageMap.put("AVATAR_LION", AVATAR_LION);
        imageMap.put("AVATAR_ZEBRA", AVATAR_ZEBRA);
        imageMap.put("AVATAR_PENGUIN", AVATAR_PENGUIN);
        imageMap.put("AVATAR_EAGLE", AVATAR_EAGLE);
        imageMap.put("AVATAR_DOLPHIN", AVATAR_DOLPHIN);
        imageMap.put("AVATAR_COALA", AVATAR_COALA);

    }

    public static PImage getImage(ImageName name) {
        return images.get(name);
    }

    public static ImageName getImageName(String s) {
        return imageMap.get(s);
    }

    public static String getImageString(ImageName im) {
        return im.name();
    }

    private static Map<ImageName, PImage> loadImages() {

        images.put(ImageName.LOGOUT, applet.loadImage("Images/logout.png"));
        images.put(ImageName.PLACEHOLDER, applet.loadImage("Images/placeholder.jpg"));
        images.put(ImageName.PLACEHOLDER_SMALL, applet.loadImage("Images/placeholder_small.jpg"));
        images.put(ImageName.SETTING, applet.loadImage("Images/setting.png"));

        images.put(ImageName.AVATAR_LION, applet.loadImage("Images/Avatar/lion.png"));
        images.put(ImageName.AVATAR_DOLPHIN, applet.loadImage("Images/Avatar/dolphin.png"));
        images.put(ImageName.AVATAR_ZEBRA, applet.loadImage("Images/Avatar/zebra.png"));
        images.put(ImageName.AVATAR_EAGLE, applet.loadImage("Images/Avatar/eagle.png"));
        images.put(ImageName.AVATAR_PENGUIN, applet.loadImage("Images/Avatar/penguin.png"));
        images.put(ImageName.AVATAR_COALA, applet.loadImage("Images/Avatar/coala.png"));

        images.put(ImageName.AVATAR_LION_SMALL, applet.loadImage("Images/Avatar/small/lion_small.png"));
        images.put(ImageName.AVATAR_DOLPHIN_SMALL, applet.loadImage("Images/Avatar/small/dolphin_small.png"));
        images.put(ImageName.AVATAR_ZEBRA_SMALL, applet.loadImage("Images/Avatar/small/zebra_small.png"));
        images.put(ImageName.AVATAR_EAGLE_SMALL, applet.loadImage("Images/Avatar/small/eagle_small.png"));
        images.put(ImageName.AVATAR_PENGUIN_SMALL, applet.loadImage("Images/Avatar/small/penguin_small.png"));
        images.put(ImageName.AVATAR_COALA_SMALL, applet.loadImage("Images/Avatar/small/coala_small.png"));

        //---------- Insert into imageMap

        images.put(ImageName.BACKGROUND_0, applet.loadImage("Images/Background/background_0.png"));
        images.put(ImageName.BACKGROUND_1, applet.loadImage("Images/Background/background_1.png"));
        images.put(ImageName.BACKGROUND_2, applet.loadImage("Images/Background/background_2.png"));
        images.put(ImageName.BACKGROUND_3, applet.loadImage("Images/Background/background_3.png"));
        images.put(ImageName.BACKGROUND_4, applet.loadImage("Images/Background/background_4.png"));
        images.put(ImageName.BACKGROUND_5, applet.loadImage("Images/Background/background_5.png"));
        images.put(ImageName.BACKGROUND_6, applet.loadImage("Images/Background/background_6.png"));
        images.put(ImageName.BACKGROUND_7, applet.loadImage("Images/Background/background_7.png"));
        images.put(ImageName.BACKGROUND_8, applet.loadImage("Images/Background/background_8.png"));
        images.put(ImageName.BACKGROUD_LILA, applet.loadImage("Images/Background/background_lila.png"));
        images.put(ImageName.BACKGROUND_GREEN, applet.loadImage("Images/Background/background_green.png"));
        images.put(ImageName.BACKGROUND_BROWN, applet.loadImage("Images/Background/background_brown.png"));
        images.put(ImageName.BACKGROUND_RED, applet.loadImage("Images/Background/background_red.png"));
        images.put(ImageName.BACKGROUND_PLAY, applet.loadImage("Images/Background/background_play.jpg"));

        images.put(ImageName.ACHIEV_0, applet.loadImage("Images/Achievements/achiev_1.png"));
        images.put(ImageName.ACHIEV_1, applet.loadImage("Images/Achievements/achiev_2.png"));
        images.put(ImageName.ACHIEV_2, applet.loadImage("Images/Achievements/achiev_3.png"));
        images.put(ImageName.ACHIEV_3, applet.loadImage("Images/Achievements/achiev_4.png"));
        images.put(ImageName.ACHIEV_4, applet.loadImage("Images/Achievements/achiev_5.png"));
        images.put(ImageName.ACHIEV_5, applet.loadImage("Images/Achievements/achiev_6.png"));
        images.put(ImageName.ACHIEV_6, applet.loadImage("Images/Achievements/achiev_7.png"));
        images.put(ImageName.ACHIEV_7, applet.loadImage("Images/Achievements/achiev_8.png"));
        images.put(ImageName.ACHIEV_8, applet.loadImage("Images/Achievements/achiev_9.png"));
        images.put(ImageName.ACHIEV_9, applet.loadImage("Images/Achievements/achiev_10.png"));
        images.put(ImageName.LOCK, applet.loadImage("Images/Achievements/achiev_lock.png"));

        images.put(ImageName.CATEGORY_CITIES, applet.loadImage("Images/Category/cities.png"));
        images.put(ImageName.CATEGORY_MOUNTAINS, applet.loadImage("Images/Category/mountains.png"));
        images.put(ImageName.CATEGORY_RIVERS, applet.loadImage("Images/Category/river.png"));
        images.put(ImageName.CATEGORY_WORLD, applet.loadImage("Images/Category/world.png"));
        images.put(ImageName.CATEGORY_LAKES, applet.loadImage("Images/Category/lake.png"));
        images.put(ImageName.CATEGORY_ISLANDS, applet.loadImage("Images/Category/island.png"));

        images.put(ImageName.LEVEL_EASY, applet.loadImage("Images/Level/easy.png"));
        images.put(ImageName.LEVEL_HARD, applet.loadImage("Images/Level/hard.png"));

        images.put(ImageName.STUDENT_PRACTISE, applet.loadImage("Images/practise.png"));
        images.put(ImageName.STUDENT_PROFILE, applet.loadImage("Images/profile.png"));
        images.put(ImageName.STUDENT_WORK, applet.loadImage("Images/work.png"));

        images.put(ImageName.GO, applet.loadImage("Images/go.png"));

        images.put(ImageName.ARROW_LEFT, applet.loadImage("Images/arrow_left.png"));
        images.put(ImageName.ARROW_RIGHT, applet.loadImage("Images/arrow_right.png"));

        images.put(ImageName.RIGHT, applet.loadImage("Images/right.png"));
        images.put(ImageName.LEFT, applet.loadImage("Images/left.png"));

        images.put(ImageName.SHOW_BACKGROUND, applet.loadImage("Images/show_background.png"));
        images.put(ImageName.SAVE, applet.loadImage("Images/save.png"));

        images.put(ImageName.READ, applet.loadImage("Images/read.png"));

        images.put(ImageName.OVERVIEW, applet.loadImage("Images/overview.png"));

        images.put(ImageName.RESET, applet.loadImage("Images/reset.png"));

        return images;
    }

}