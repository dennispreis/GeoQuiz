/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.List;

/**
 *
 * @author User
 */
public class Practice
{
    
    private int paper_id;

    private int practice_id;

    private List<Question> questionList;

    public Practice(int practice_id, Paper p)
    {
        this.paper_id = p.getId();
        this.practice_id = practice_id;
        this.questionList = p.getQuestions();
    }

    
    
    public List<Question> getQuestionList()
    {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList)
    {
        this.questionList = questionList;
    }
    
    
    /**
     * Get the value of practice_id
     *
     * @return the value of practice_id
     */
    public int getPractice_id()
    {
        return practice_id;
    }

    /**
     * Set the value of practice_id
     *
     * @param practice_id new value of practice_id
     */
    public void setPractice_id(int practice_id)
    {
        this.practice_id = practice_id;
    }

    /**
     * Get the value of paper_id
     *
     * @return the value of paper_id
     */
    public int getPaper_id()
    {
        return paper_id;
    }

    /**
     * Set the value of paper_id
     *
     * @param paper_id new value of paper_id
     */
    public void setPaper_id(int paper_id)
    {
        this.paper_id = paper_id;
    }

}
