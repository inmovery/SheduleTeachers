package hse.sheduleteachers.Calendar.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import hse.sheduleteachers.Calendar.adapter.viewholder.BaseDayHolder;
import hse.sheduleteachers.Calendar.utils.Constants;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DayOfWeekHolder extends BaseDayHolder {

    private SimpleDateFormat mDayOfWeekFormatter;

    public DayOfWeekHolder(View itemView, CalendarView calendarView) {
        super(itemView, calendarView);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day_name);
        mDayOfWeekFormatter = new SimpleDateFormat(Constants.DAY_NAME_FORMAT, Locale.getDefault());
    }

    public void bind(Day day) {
        tvDay.setText(mDayOfWeekFormatter.format(day.getCalendar().getTime()));
        tvDay.setTextColor(calendarView.getWeekDayTitleTextColor());
    }
}