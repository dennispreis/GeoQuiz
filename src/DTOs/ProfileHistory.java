package DTOs;

import GameManager.Category;
import GameManager.Level;

import java.util.Date;

public class ProfileHistory {

    private int startIndex, endIndex;
    private HistoryRecord[] historyRecords;

    public ProfileHistory() {
        startIndex = 0;
        endIndex = 5;
        historyRecords = new HistoryRecord[]{
                new HistoryRecord(Category.CITIES, Level.HARD, new Date(System.currentTimeMillis())),
                new HistoryRecord(Category.MOUNTAINS, Level.EASY, new Date(System.currentTimeMillis()))
        };
    }

    public HistoryRecord[] getFiveRecords() {
        HistoryRecord[] tmp = new HistoryRecord[5];
        try {
            for (int i = startIndex, counter = 0; i < endIndex; i++, counter++) {
                if (historyRecords[i] != null) tmp[counter] = historyRecords[i];
                else tmp[counter] = null;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        return tmp;
    }

    public void increaseIndex() {
        if (endIndex != historyRecords.length) {
            startIndex++;
            endIndex++;
        }
    }

    public void decreaseIndex() {
        if (startIndex != 0) {
            startIndex--;
            endIndex--;
        }
    }
}
