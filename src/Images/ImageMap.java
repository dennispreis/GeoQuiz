/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Images;

import static Images.ImageName.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tunjing
 */
public class ImageMap
{
    private static Map<String, ImageName> imageMap = new HashMap<>();

    public ImageMap()
    {
        imageMap.put("AVATAR_LION", AVATAR_LION);
        imageMap.put("AVATAR_ZEBRA", AVATAR_ZEBRA);
        imageMap.put("AVATAR_PENGUIN", AVATAR_PENGUIN);
        imageMap.put("AVATAR_EAGLE", AVATAR_EAGLE);
        imageMap.put("AVATAR_DOLPHIN", AVATAR_DOLPHIN);
        imageMap.put("AVATAR_COALA", AVATAR_COALA);
    }   
    
    public static ImageName getImageName(String s)
    {
        return imageMap.get(s);
    }
    
    public static String getImageString(ImageName im)
    {
        return im.name();
    }
    
}
