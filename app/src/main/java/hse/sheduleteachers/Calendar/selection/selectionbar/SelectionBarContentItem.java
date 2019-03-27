package hse.sheduleteachers.Calendar.selection.selectionbar;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarItem;

public class SelectionBarContentItem implements SelectionBarItem {

    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public SelectionBarContentItem(Day day) {
        this.day = day;
    }
}

