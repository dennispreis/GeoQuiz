/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManager;

import static GameManager.Category.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class CategoryMap
{
    public Map<String,Category> categoryMap = new HashMap<>();
    
    public CategoryMap(){
        categoryMap.put("CITIES", CITIES);
        categoryMap.put("MOUNTAINS", MOUNTAINS);
        categoryMap.put("RIVERS", RIVERS);
    };
}