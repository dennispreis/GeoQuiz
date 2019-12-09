package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ProfileHistory
{

    private int start, end, range;
    private int actualPage;
    private int maxPages;
    private List<HistoryRecord> historyRecordsList;
    private int range;

    public ProfileHistory()
    {
        historyRecordsList = new ArrayList<>();
        range = 5;
        start = 0;
        range = 5;
        end = range;
        actualPage = 0;
        maxPages = 0;
    }

    public ProfileHistory(int r)
    {
        historyRecordsList = new ArrayList<>();
        start = 0;
        range = r;
        end = range;
        actualPage = 0;
        maxPages = 0;
    }

    public int getMaxPages()
    {
        return maxPages;
    }


    void setMaxPages()
    {
        this.maxPages = historyRecordsList.size() / range;
    }

    public int getActualPage()
    {
        return this.actualPage;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public void increaseRange() {

        start += range;
        end += range;
        actualPage++;
    }

    public void decreaseRange()
    {
        start -= range;
        end -= range;
        actualPage--;
    }

    public List<HistoryRecord> getHistoryRecord()
    {
        return this.historyRecordsList;
    }
}
