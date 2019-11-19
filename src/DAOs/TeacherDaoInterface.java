/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Teacher;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface TeacherDaoInterface
{

    int getAccountId(String loginName);

    String getHash(String loginName);

    String getLastDateTime(String loginName);

    void setLastDateTime(String loginName, String dateTime);

    public Teacher createTeacherUser(int id);

    public ArrayList<String> getTeacherUsernames();

    public int getAttempt(String loginName);

    public void setAttempt(String loginName, int attempt);

    public String getInvalidDateTime(String loginName);

    public void setInvalidDateTime(String loginName, String dateTime);
    
    public int getPassCode(String loginName);
    
    public void setHash(String loginName, String hash);
}
