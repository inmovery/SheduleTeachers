package hse.sheduleteachers.Calendar.dialog;

import hse.sheduleteachers.Calendar.model.Day;

import java.util.List;

public interface OnDaysSelectionListener {
    void onDaysSelected(List<Day> selectedDays);
}
