/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Student;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface StudentDaoInterface
{

    boolean isAccountExisting(String loginName, String loginPassword);

    int getAccountId(String loginName, String loginPassword);

    Student createStudentUser(int id);

    ArrayList<String> getStudentUsernames();

    boolean saveStudent(Student s);
    
    boolean saveStudentNickName(Student s);
    
    boolean saveStudentAvatar(Student s);
    
}
