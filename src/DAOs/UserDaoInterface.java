/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.util.ArrayList;

/**
 *
 * @author DTOs.User
 */
public interface UserDaoInterface
{
    boolean isAccountExisting(String loginName, String loginPassword);
    int getAccountId(String loginName, String loginPassword, boolean isTeacher);
    String getHash(String loginName);
    String getDateTime(String loginName);
    void setDateTime(String loginName, String dateTime);
    ArrayList<String> getUsernames();
}
