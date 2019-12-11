/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import DAOs.ClassDaoInterface;
import DAOs.MyClassDao;
import DAOs.MyPracticeDao;
import DAOs.PracticeDaoInterface;
import java.util.List;

/**
 *
 * @author User
 */
public class Teacher extends User
{

    private List<Class> classList;
    private ProfileHistory classHistory;
    private static PracticeDaoInterface IPracticeDao = new MyPracticeDao();
    private static ClassDaoInterface IClassDao = new MyClassDao();
    public Teacher(int id, String name)
    {
        super(id, name);
        classList = IClassDao.getClassByTeacherId(id);
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

    public List<Class> getClassList()
    {
        return classList;
    }

    public void setClassList(List<Class> classList)
    {
        this.classList = classList;
    }
}
