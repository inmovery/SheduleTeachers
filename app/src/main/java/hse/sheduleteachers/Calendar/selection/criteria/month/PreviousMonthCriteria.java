package hse.sheduleteachers.Calendar.selection.criteria.month;

import hse.sheduleteachers.Calendar.selection.criteria.month.BaseMonthCriteria;
import hse.sheduleteachers.Calendar.utils.DateUtils;

import java.util.Calendar;

public class PreviousMonthCriteria extends BaseMonthCriteria {

    private long currentTimeInMillis;

    public PreviousMonthCriteria() {
        currentTimeInMillis = System.currentTimeMillis();
    }

    @Override
    protected int getMonth() {
        Calendar calendar = DateUtils.getCalendar(currentTimeInMillis);
        calendar.add(Calendar.MONTH, -1);
        return calendar.get(Calendar.MONTH);
    }

    @Override
    protected int getYear() {
        Calendar calendar = DateUtils.getCalendar(currentTimeInMillis);
        calendar.add(Calendar.MONTH, -1);
        return calendar.get(Calendar.YEAR);
    }
}
