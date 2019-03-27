package hse.sheduleteachers.Calendar.view.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hse.sheduleteachers.Calendar.view.delegate.BaseDelegate;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.adapter.viewholder.DayOfWeekHolder;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.view.CalendarView;

public class DayOfWeekDelegate extends BaseDelegate {

    public DayOfWeekDelegate(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public DayOfWeekHolder onCreateDayHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_day_of_week, parent, false);
        return new DayOfWeekHolder(view, calendarView);
    }

    public void onBindDayHolder(Day day, DayOfWeekHolder holder, int position) {
        holder.bind(day);
    }
}
