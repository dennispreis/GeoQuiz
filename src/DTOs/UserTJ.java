/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author User
 */
public class UserTJ
{

    private int id;
    private String name;
    private String avatar;

    
    public UserTJ(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.avatar = "DEFAULTPATH";
    }

    
    public UserTJ(int id, String name, String avatar)
    {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the value of avatar
     *
     * @return the value of avatar
     */
    public String getAvatar()
    {
        return avatar;
    }

    /**
     * Set the value of avatar
     *
     * @param avatar new value of avatar
     */
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
}
