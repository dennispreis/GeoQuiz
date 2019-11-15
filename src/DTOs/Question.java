/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author User
 * @author DTOs.User
 */
public class Question
{
    
    private int id;
    private String question_type;
    private String type;
    private String region;    
    private String question_text;
    private String answer_one;
    private String answer_two;
    private String answer_three;
    private String answer_four;

    private String correct_answer;

    public Question(int id, String question_type, String type, String region, String question_text, String answer_one, String answer_two, String answer_three, String answer_four, String correct_answer)
    {
        this.id = id;
        this.question_type = question_type;
        this.type = type;
        this.region = region;
        this.question_text = question_text;
        this.answer_one = answer_one;
        this.answer_two = answer_two;
        this.answer_three = answer_three;
        this.answer_four = answer_four;
        this.correct_answer = correct_answer;
    }

    




    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id)
    {
        this.id = id;
    }
        /**
     * Get the value of question_type
     *
     * @return the value of question_type
     */
    public String getQuestion_type()
    {
        return question_type;
    }

    /**
     * Set the value of question_type
     *
     * @param question_type new value of question_type
     */
    public void setQuestion_type(String question_type)
    {
        this.question_type = question_type;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type)
    {
        this.type = type;
    }
    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
        /**
     * Get the value of question_text
     *
     * @return the value of question_text
     */
    public String getQuestion_text()
    {
        return question_text;
    }

    /**
     * Set the value of question_text
     *
     * @param question_text new value of question_text
     */
    public void setQuestion_text(String question_text)
    {
        this.question_text = question_text;
    }
    /**
     * Get the value of answer_one
     *
     * @return the value of answer_one
     */
    public String getAnswer_one()
    {
        return answer_one;
    }

    /**
     * Set the value of answer_one
     *
     * @param answer_one new value of answer_one
     */
    public void setAnswer_one(String answer_one)
    {
        this.answer_one = answer_one;
    }
    /**
     * Get the value of answer_two
     *
     * @return the value of answer_two
     */
    public String getAnswer_two()
    {
        return answer_two;
    }

    /**
     * Set the value of answer_two
     *
     * @param answer_two new value of answer_two
     */
    public void setAnswer_two(String answer_two)
    {
        this.answer_two = answer_two;
    }

    /**
     * Get the value of answer_three
     *
     * @return the value of answer_three
     */
    public String getAnswer_three()
    {
        return answer_three;
    }

    /**
     * Set the value of answer_three
     *
     * @param answer_three new value of answer_three
     */
    public void setAnswer_three(String answer_three)
    {
        this.answer_three = answer_three;
    }
        /**
     * Get the value of answer_four
     *
     * @return the value of answer_four
     */
    public String getAnswer_four()
    {
        return answer_four;
    }

    /**
     * Set the value of answer_four
     *
     * @param answer_four new value of answer_four
     */
    public void setAnswer_four(String answer_four)
    {
        this.answer_four = answer_four;
    }


    /**
     * Get the value of correct_answer
     *
     * @return the value of correct_answer
     */
    public String getCorrect_answer()
    {
        return correct_answer;
    }

    /**
     * Set the value of correct_answer
     *
     * @param correct_answer new value of correct_answer
     */
    public void setCorrect_answer(String correct_answer)
    {
        this.correct_answer = correct_answer;
    }

}
