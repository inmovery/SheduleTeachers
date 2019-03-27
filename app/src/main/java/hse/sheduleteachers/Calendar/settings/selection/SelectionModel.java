package hse.sheduleteachers.Calendar.settings.selection;

import hse.sheduleteachers.Calendar.settings.selection.SelectionInterface;
import hse.sheduleteachers.Calendar.utils.SelectionType;

public class SelectionModel implements SelectionInterface {

    //Selecton type SINGLE, MULTIPLE, RANGE, NONE
    @SelectionType
    private int selectionType;

    @Override
    @SelectionType
    public int getSelectionType() {
        return selectionType;
    }

    @Override
    public void setSelectionType(@SelectionType int selectionType) {
        this.selectionType = selectionType;
    }
}
