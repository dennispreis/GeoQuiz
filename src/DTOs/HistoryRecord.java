package DTOs;

import GameManager.Category;
import GameManager.Level;

import java.util.Date;

public class HistoryRecord {
    private Category category;
    private Level level;
    private Date date;

    public HistoryRecord(Category category, Level level, Date date) {
        this.category = category;
        this.level = level;
        this.date = date;
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