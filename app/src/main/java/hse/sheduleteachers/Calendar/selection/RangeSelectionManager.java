package hse.sheduleteachers.Calendar.selection;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.BaseSelectionManager;
import hse.sheduleteachers.Calendar.selection.OnDaySelectedListener;
import hse.sheduleteachers.Calendar.selection.SelectionState;
import hse.sheduleteachers.Calendar.utils.DateUtils;

public class RangeSelectionManager extends BaseSelectionManager {

    private Pair<Day, Day> days;
    private Day tempDay;

    public RangeSelectionManager(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    public Pair<Day, Day> getDays() {
        return days;
    }

    @Override
    public void toggleDay(@NonNull Day day) {
        if (days == null && tempDay == null || tempDay == null) {
            tempDay = day;
            days = null;
        } else {
            if (tempDay == day) {
                return;
            }
            if (tempDay.getCalendar().getTime().before(day.getCalendar().getTime())) {
                days = Pair.create(tempDay, day);
            } else {
                days = Pair.create(day, tempDay);
            }
            tempDay = null;
        }
        onDaySelectedListener.onDaySelected();
    }

    @Override
    public boolean isDaySelected(@NonNull Day day) {
        return isDaySelectedManually(day);
    }

    private boolean isDaySelectedManually(@NonNull Day day) {
        if (tempDay != null) {
            return day.equals(tempDay);
        } else if (days != null) {
            return DateUtils.isDayInRange(day, days.first, days.second);
        } else {
            return false;
        }
    }

    @Override
    public void clearSelections() {
        days = null;
        tempDay = null;
    }

    public hse.sheduleteachers.Calendar.selection.SelectionState getSelectedState(Day day) {
        if (!isDaySelectedManually(day)) {
            return hse.sheduleteachers.Calendar.selection.SelectionState.SINGLE_DAY;
        }

        if (days == null) {
            return hse.sheduleteachers.Calendar.selection.SelectionState.START_RANGE_DAY_WITHOUT_END;
        } else if (days.first.equals(day)) {
            return hse.sheduleteachers.Calendar.selection.SelectionState.START_RANGE_DAY;
        } else if (days.second.equals(day)) {
            return hse.sheduleteachers.Calendar.selection.SelectionState.END_RANGE_DAY;
        } else if (DateUtils.isDayInRange(day, days.first, days.second)) {
            return hse.sheduleteachers.Calendar.selection.SelectionState.RANGE_DAY;
        } else {
            return SelectionState.SINGLE_DAY;
        }
    }
}
