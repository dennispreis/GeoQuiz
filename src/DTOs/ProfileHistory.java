package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ProfileHistory {

    private int start, end;
    private int actualPage;
    private int maxPages;
    private List<HistoryRecord> historyRecordsList;

    public ProfileHistory() {
        historyRecordsList = new ArrayList<>();
        start = 0;
        end = 5;
        actualPage = 0;
        maxPages = 0;
    }

    public int getMaxPages() {
        return maxPages;
    }

    void setMaxPages() {
        this.maxPages = historyRecordsList.size() / 5;
    }

    public int getActualPage() {
        return this.actualPage;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void increaseRange() {
        start += 5;
        end += 5;
        actualPage++;
    }

    public void decreaseRange() {
        start -= 5;
        end -= 5;
        actualPage--;
    }

    public List<HistoryRecord> getHistoryRecord() {
        return this.historyRecordsList;
    }
}
