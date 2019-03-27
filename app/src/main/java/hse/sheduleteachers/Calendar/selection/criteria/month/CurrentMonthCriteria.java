package hse.sheduleteachers.Calendar.selection.criteria.month;

import hse.sheduleteachers.Calendar.selection.criteria.month.BaseMonthCriteria;
import hse.sheduleteachers.Calendar.utils.DateUtils;

import java.util.Calendar;

public class CurrentMonthCriteria extends BaseMonthCriteria {

    private long currentTimeInMillis;

    public CurrentMonthCriteria() {
        currentTimeInMillis = System.currentTimeMillis();
    }

    @Override
    protected int getMonth() {
        return DateUtils.getCalendar(currentTimeInMillis).get(Calendar.MONTH);
    }

    @Override
    protected int getYear() {
        return DateUtils.getCalendar(currentTimeInMillis).get(Calendar.YEAR);
    }
}
