/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import DAOs.MyPracticeDao;
import DAOs.PracticeDaoInterface;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Teacher extends User
{

    private ArrayList<Class> classList;
    private ProfileHistory classHistory;
    private static PracticeDaoInterface IPracticeDao = new MyPracticeDao();

    public Teacher(int id, String name)
    {
        super(id, name);
        classList = new ArrayList<>();
    }

    public void setProfileHistory(String className)
    {
        this.classHistory = IPracticeDao.getPracticeProfileHistoryByClass(className);
        classHistory.setMaxPages();
    }

    public ProfileHistory getProfileHistory()
    {
        return this.classHistory;
    }
}
