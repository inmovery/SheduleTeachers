package hse.sheduleteachers.Calendar.selection;

import android.support.annotation.NonNull;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.OnDaySelectedListener;

public abstract class BaseSelectionManager {

    protected OnDaySelectedListener onDaySelectedListener;

    public abstract void toggleDay(@NonNull Day day);

    public abstract boolean isDaySelected(@NonNull Day day);

    public abstract void clearSelections();

    public BaseSelectionManager() {
    }
}
