/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import com.sun.deploy.net.proxy.pac.PACFunctions;
import processing.core.PApplet;

/**
 * @author User
 * @author DTOs.User
 */
public abstract class Question {
    public PApplet applet;
    private int id;
    private String question_type;
    private String type;
    private String region;
    private String question_text;
    private String correct_answer;

    public Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer) {
        this.applet = applet;
        this.id = id;
        this.question_type = question_type;
        this.type = type;
        this.region = region;
        this.question_text = question_text;
        this.correct_answer = correct_answer;
    }

    public abstract void show();

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
     * Get the value of question_type
     *
     * @return the value of question_type
     */
    public String getQuestion_type() {
        return question_type;
    }

    /**
     * Set the value of question_type
     *
     * @param question_type new value of question_type
     */
    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Get the value of question_text
     *
     * @return the value of question_text
     */
    public String getQuestion_text() {
        return question_text;
    }

    /**
     * Set the value of question_text
     *
     * @param question_text new value of question_text
     */
    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    /**
     * Get the value of correct_answer
     *
     * @return the value of correct_answer
     */
    public String getCorrect_answer() {
        return correct_answer;
    }

    /**
     * Set the value of correct_answer
     *
     * @param correct_answer new value of correct_answer
     */
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

}
