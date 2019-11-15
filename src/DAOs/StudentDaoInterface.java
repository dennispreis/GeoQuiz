/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Student;

/**
 *
 * @author User
 */
public interface StudentDaoInterface
{
    boolean isAccountExisting(String loginName, String loginPassword);
    int getAccountId(String loginName, String loginPassword, boolean isTeacher);
    Student createStudent(int id);
    
    boolean updateAvatar();
    boolean updateNickname();
}
