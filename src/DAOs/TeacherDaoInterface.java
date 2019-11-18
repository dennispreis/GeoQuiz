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

 
    int getAccountId(String loginName, String loginPassword);

    String getHash(String loginName);

    String getDateTime(String loginName);

    void setDateTime(String loginName, String dateTime);

    public Teacher createTeacherUser(int id);
     
    ArrayList<String> getUsernames();
    
    public ArrayList<String> getTeacherUsernames();

}
