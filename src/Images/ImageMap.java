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
 *
 * @author Tunjing
 */
public class ImageMap
{

    private static PApplet applet;
    private static Map<String, ImageName> imageMap = new HashMap<>();
    private static Map<ImageName, PImage> images = images = new HashMap<>();

    
    
    public ImageMap(PApplet applet)
    {
        this.applet = applet;

        images = loadImages();
        imageMap.put("AVATAR_LION", AVATAR_LION);
        imageMap.put("AVATAR_ZEBRA", AVATAR_ZEBRA);
        imageMap.put("AVATAR_PENGUIN", AVATAR_PENGUIN);
        imageMap.put("AVATAR_EAGLE", AVATAR_EAGLE);
        imageMap.put("AVATAR_DOLPHIN", AVATAR_DOLPHIN);
        imageMap.put("AVATAR_COALA", AVATAR_COALA);

    }

    public static PImage getImage(ImageName name)
    {
        return images.get(name);
    }

    public static ImageName getImageName(String s)
    {
        return imageMap.get(s);
    }

    public static String getImageString(ImageName im)
    {
        return im.name();
    }

    private static Map<ImageName, PImage> loadImages()
    {

        images.put(ImageName.BACKGROUND, applet.loadImage("Images/background.JPG"));
        images.put(ImageName.LOGOUT, applet.loadImage("Images/logout.png"));
        images.put(ImageName.PLACEHOLDER, applet.loadImage("Images/placeholder.jpg"));
        images.put(ImageName.PLACEHOLDER_SMALL, applet.loadImage("Images/placeholder_small.jpg"));

        images.put(ImageName.AVATAR_LION, applet.loadImage("Images/Avatars/lion.png"));
        images.put(ImageName.AVATAR_DOLPHIN, applet.loadImage("Images/Avatars/dolphin.png"));
        images.put(ImageName.AVATAR_ZEBRA, applet.loadImage("Images/Avatars/zebra.png"));
        images.put(ImageName.AVATAR_EAGLE, applet.loadImage("Images/Avatars/eagle.png"));
        images.put(ImageName.AVATAR_PENGUIN, applet.loadImage("Images/Avatars/penguin.png"));
        images.put(ImageName.AVATAR_COALA, applet.loadImage("Images/Avatars/coala.png"));

        images.put(ImageName.AVATAR_LION_SMALL, applet.loadImage("Images/Avatars/small/lion_small.png"));
        images.put(ImageName.AVATAR_DOLPHIN_SMALL, applet.loadImage("Images/Avatars/small/dolphin_small.png"));
        images.put(ImageName.AVATAR_ZEBRA_SMALL, applet.loadImage("Images/Avatars/small/zebra_small.png"));
        images.put(ImageName.AVATAR_EAGLE_SMALL, applet.loadImage("Images/Avatars/small/eagle_small.png"));
        images.put(ImageName.AVATAR_PENGUIN_SMALL, applet.loadImage("Images/Avatars/small/penguin_small.png"));
        images.put(ImageName.AVATAR_COALA_SMALL, applet.loadImage("Images/Avatars/small/coala_small.png"));

        return images;
    }

}
