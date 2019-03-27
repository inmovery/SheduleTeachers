package hse.sheduleteachers.Calendar.selection;

import android.support.annotation.NonNull;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.BaseCriteriaSelectionManager;
import hse.sheduleteachers.Calendar.selection.OnDaySelectedListener;
import hse.sheduleteachers.Calendar.selection.criteria.BaseCriteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleSelectionManager extends BaseCriteriaSelectionManager {

    private final Set<Day> days = new HashSet<>();

    public MultipleSelectionManager(hse.sheduleteachers.Calendar.selection.OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    public MultipleSelectionManager(BaseCriteria criteria, hse.sheduleteachers.Calendar.selection.OnDaySelectedListener onDaySelectedListener) {
        this(new ArrayList<>(Collections.singleton(criteria)), onDaySelectedListener);
    }

    public MultipleSelectionManager(List<BaseCriteria> criteriaList, OnDaySelectedListener onDaySelectedListener) {
        this.criteriaList = criteriaList;
        this.onDaySelectedListener = onDaySelectedListener;
    }

    @Override
    public void toggleDay(@NonNull Day day) {
        if (days.contains(day)) {
            days.remove(day);
        } else {
            days.add(day);
        }
        onDaySelectedListener.onDaySelected();
    }

    @Override
    public boolean isDaySelected(@NonNull Day day) {
        return days.contains(day) || isDaySelectedByCriteria(day);
    }

    @Override
    public void clearSelections() {
        days.clear();
    }

    public void removeDay(Day day) {
        days.remove(day);
        onDaySelectedListener.onDaySelected();
    }
}
