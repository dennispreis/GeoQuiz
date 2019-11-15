/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Teacher;

/**
 *
 * @author User
 */
public interface TeacherDaoInterface
{

    boolean isAccountExisting(String loginName, String loginPassword);
    int getAccountId(String loginName, String loginPassword, boolean isTeacher);
    Teacher createTeacher(int id);

}
