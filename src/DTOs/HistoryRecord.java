package DTOs;

import GameManager.Category;
import GameManager.Level;

import java.util.Date;

public class HistoryRecord
{
    private String student_name;
    private int record_id;
    private Category category;
    private Level level;
    private int score;
    private Date date;

    public HistoryRecord(int record_id, Category category, Level level, int score, Date date)
    {
        this.record_id = record_id;
        this.category = category;
        this.level = level;
        this.score = score;
        this.date = date;
    }

    public HistoryRecord(String student_name,int record_id, Category category, Level level, int score, Date date)
    {
        this.student_name = student_name;
        this.record_id = record_id;
        this.category = category;
        this.level = level;
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

    public Level getLevel()
    {
        return level;
    }

    public void setLevel(Level level)
    {
        this.level = level;
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
    
    
}
