package DTOs;

import GameManager.Category;
import GameManager.Level;

import java.util.Date;

public class HistoryRecord
{
    private String student_name;
    private int record_id;
    private String test_name;
    private Category category;
    private int score;
    private Date date;

    public HistoryRecord(int record_id, Category category, int score, Date date)
    {
        this.record_id = record_id;
        this.category = category;
        this.score = score;
        this.date = date;
    }

    public HistoryRecord(String student_name,int record_id, Category category, int score, Date date)
    {
        this.student_name = student_name;
        this.record_id = record_id;
        this.category = category;
        this.score = score;
        this.date = date;
    }

    
    public HistoryRecord(String student_name,String test_name,int record_id, int score, Date date)
    {
        this.student_name = student_name;
        this.test_name = test_name;
        this.record_id = record_id;
        this.score = score;
        this.date = date;
    }

    public HistoryRecord(int id, Date date)
    {
        this.record_id = id;
        this.date = date;
    }

    public int getRecord_id()
    {
        return record_id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getStudent_name()
    {
        return student_name;
    }

    public void setStudent_name(String student_name)
    {
        this.student_name = student_name;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    public String getTest_name()
    {
        return test_name;
    }
}
