/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.List;

/**
 * @author User
 */
public class Paper {

    private int id;
    private List<Question> questions;

    public Paper(int id) {
        this.id = id;
    }

    public Paper(int id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of questions
     *
     * @return the value of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Set the value of questions
     *
     * @param questions new value of questions
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
