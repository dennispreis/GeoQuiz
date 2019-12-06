package DTOs;

import GameManager.Category;
import GameManager.Level;

import java.util.Date;

public class HistoryRecord {

    private int record_id;
    private Category category;
    private Level level;
    private Date date;

    public HistoryRecord(int record_id, Category category, Level level, Date date) {
        this.record_id = record_id;
        this.category = category;
        this.level = level;
        this.date = date;
    }

    public HistoryRecord(int id, Date date){
        this.record_id = id;
        this.date = date;
    }

    public int getRecord_id() {
        return record_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}