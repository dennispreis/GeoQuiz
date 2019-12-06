/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import DAOs.MyPracticeDao;
import DAOs.PracticeDaoInterface;

public class Student extends User {

    private int class_id;
    private String nickname;
    private String avatar;
    private ProfileHistory profileHistory;
    private static PracticeDaoInterface IPracticeDao;

    public Student(int class_id, String nickname, int id, String name, String avatar) {
        super(id, name);
        IPracticeDao = new MyPracticeDao();
        this.class_id = class_id;
        this.nickname = nickname;
        this.avatar = avatar;
        profileHistory = IPracticeDao.getProfileHistory(id);
        profileHistory.setMaxPages();
    }

    public int getClass_id() {
        return class_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ProfileHistory getProfileHistory() {
        return profileHistory;
    }
}

