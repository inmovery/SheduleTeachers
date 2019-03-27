package hse.sheduleteachers.Calendar.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import hse.sheduleteachers.Calendar.adapter.viewholder.BaseDayHolder;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.view.CalendarView;

public class OtherDayHolder extends BaseDayHolder {

    public OtherDayHolder(View itemView, CalendarView calendarView) {
        super(itemView, calendarView);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day_number);
    }

    public void bind(Day day) {
        tvDay.setText(String.valueOf(day.getDayNumber()));
        tvDay.setTextColor(calendarView.getOtherDayTextColor());
    }
}
