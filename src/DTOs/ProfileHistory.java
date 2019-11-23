package DTOs;

import DAOs.MyPracticeDao;
import DAOs.PracticeDaoInterface;
import GameManager.Category;
import GameManager.Level;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class ProfileHistory
{

    private int id;
    private int startIndex, endIndex;
    private HistoryRecord[] historyRecords;
    private List<HistoryRecord> historyRecordsList;
    private PracticeDaoInterface IPracticeDao = new MyPracticeDao();

    public ProfileHistory()
    {
        startIndex = 0;
        endIndex = 5;
        historyRecords = new HistoryRecord[]
        {
            new HistoryRecord(Category.CITIES, Level.HARD, new Date(System.currentTimeMillis())),
            new HistoryRecord(Category.MOUNTAINS, Level.EASY, new Date(System.currentTimeMillis()))
        };
        historyRecordsList = new ArrayList<>();
    }

    public List<HistoryRecord> getHistoryRecord()
    {
        return this.historyRecordsList;
    }

    public HistoryRecord[] getFiveRecords()
    {

        for (int i = 0; i < historyRecordsList.size(); i++)
        {
            historyRecords[i] = historyRecordsList.get(i);
        }
        HistoryRecord[] tmp = new HistoryRecord[5];

        try
        {
            for (int i = startIndex, counter = 0; i < endIndex; i++, counter++)
            {
                if (historyRecords[i] != null)
                {
                    tmp[counter] = historyRecords[i];
                } else
                {
                    tmp[counter] = null;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex)
        {
        }
        return tmp;

    }

    public void increaseIndex()
    {
        if (endIndex != historyRecords.length)
        {
            startIndex++;
            endIndex++;
        }
    }

    public void decreaseIndex()
    {
        if (startIndex != 0)
        {
            startIndex--;
            endIndex--;
        }
    }
}
