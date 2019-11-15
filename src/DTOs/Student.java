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
public class Student extends User
{

    private int class_id;
    private String nickname;
    private String avatar;
    private static ProfileHistory profileHistory;

    public Student(int id, String name)
    {
        super(id, name);
    }

    public Student(int class_id, String nickname, int id, String name)
    {
        super(id, name);
        this.class_id = class_id;
        this.nickname = nickname;
    }

    public Student(int class_id, String nickname, int id, String name, String avatar)
    {
        super(id, name);
        this.class_id = class_id;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    /**
     * Get the value of class_id
     *
     * @return the value of class_id
     */
    public int getClass_id()
    {
        return class_id;
    }

    /**
     * Set the value of class_id
     *
     * @param class_id new value of class_id
     */
    public void setClass_id(int class_id)
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

    public String getAvatar()
    {
        return this.avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    
    
    public ProfileHistory getProfileHistory(){
        return profileHistory;
    }
}

