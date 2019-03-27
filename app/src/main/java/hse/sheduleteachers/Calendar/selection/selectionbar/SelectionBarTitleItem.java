package hse.sheduleteachers.Calendar.selection.selectionbar;

import hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarItem;

public class SelectionBarTitleItem implements SelectionBarItem {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SelectionBarTitleItem(String title) {
        this.title = title;
    }
}
