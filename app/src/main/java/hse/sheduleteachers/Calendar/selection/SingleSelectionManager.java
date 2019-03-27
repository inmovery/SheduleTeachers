package hse.sheduleteachers.Calendar.selection;

import android.support.annotation.NonNull;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.BaseSelectionManager;
import hse.sheduleteachers.Calendar.selection.OnDaySelectedListener;

public class SingleSelectionManager extends BaseSelectionManager {

    private Day day;

    public SingleSelectionManager(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    @Override
    public void toggleDay(@NonNull Day day) {
        this.day = day;
        onDaySelectedListener.onDaySelected();
    }

    @Override
    public boolean isDaySelected(@NonNull Day day) {
        return day.equals(this.day);
    }

    @Override
    public void clearSelections() {
        day = null;
    }
}
