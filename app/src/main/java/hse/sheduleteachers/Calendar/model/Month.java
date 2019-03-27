package hse.sheduleteachers.Calendar.model;

import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.model.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Month {

    private List<hse.sheduleteachers.Calendar.model.Day> days;
    private hse.sheduleteachers.Calendar.model.Day firstDay;

    public Month(hse.sheduleteachers.Calendar.model.Day firstDay, List<hse.sheduleteachers.Calendar.model.Day> days) {
        this.days = days;
        this.firstDay = firstDay;
    }

    public hse.sheduleteachers.Calendar.model.Day getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(hse.sheduleteachers.Calendar.model.Day firstDay) {
        this.firstDay = firstDay;
    }

    public List<hse.sheduleteachers.Calendar.model.Day> getDays() {
        return days;
    }

    /**
     * Returns selected days that belong only to current month
     *
     * @return
     */
    public List<hse.sheduleteachers.Calendar.model.Day> getDaysWithoutTitlesAndOnlyCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDay.getCalendar().getTime());
        int currentMonth = calendar.get(Calendar.MONTH);

        List<hse.sheduleteachers.Calendar.model.Day> result = new ArrayList<>();
        for (Day day : days) {
            calendar.setTime(day.getCalendar().getTime());
            if (!(day instanceof DayOfWeek) && calendar.get(Calendar.MONTH) == currentMonth) {
                result.add(day);
            }
        }
        return result;
    }

    public String getMonthName() {
        return new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(firstDay.getCalendar().getTime());
    }


}
