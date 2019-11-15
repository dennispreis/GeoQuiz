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
public class Student extends UserTJ
{

    private String class_id;
    private String nickname;

    public Student(int id, String name)
    {
        super(id, name);
    }

    public Student(int id, String name, String avatar)
    {
        super(id, name, avatar);
    }

    public Student(String class_id, String nickname, int id, String name)
    {
        super(id, name);
        this.class_id = class_id;
        this.nickname = nickname;
    }

    public Student(String class_id, String nickname, int id, String name, String avatar)
    {
        super(id, name, avatar);
        this.class_id = class_id;
        this.nickname = nickname;
    }

    /**
     * Get the value of class_id
     *
     * @return the value of class_id
     */
    public String getClass_id()
    {
        return class_id;
    }

    /**
     * Set the value of class_id
     *
     * @param class_id new value of class_id
     */
    public void setClass_id(String class_id)
    {
        this.class_id = class_id;
    }

    /**
     * Get the value of nickname
     *
     * @return the value of nickname
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * Set the value of nickname
     *
     * @param nickname new value of nickname
     */
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

}
