/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Teacher extends UserTJ
{
    private ArrayList<Class> classList;
    
    public Teacher(int id, String name, String avatar)
    {
        super(id, name, avatar);
    }

    public Teacher(int id, String name)
    {
        super(id, name);
    }

}
