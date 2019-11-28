/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManager;

import static GameManager.Level.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class LevelMap
{

    public Map<String, Level> levelMap = new HashMap<>();

    public LevelMap()
    {
        levelMap.put("EASY", EASY);
        levelMap.put("MEDIUM", MEDIUM);
        levelMap.put("HARD", HARD);
        levelMap.put("tmp", tmp);
    }
;
}
