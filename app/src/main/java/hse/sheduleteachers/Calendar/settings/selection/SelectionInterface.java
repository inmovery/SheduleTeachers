package hse.sheduleteachers.Calendar.settings.selection;

import hse.sheduleteachers.Calendar.utils.SelectionType;

public interface SelectionInterface {

    @SelectionType
    int getSelectionType();

    void setSelectionType(@SelectionType int selectionType);
}
