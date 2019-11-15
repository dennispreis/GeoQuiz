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
public class User
{

    private int id;
    private String name;

    
    public User(int id, String name)
    {
        this.id = id;
        this.name = name;
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

}
