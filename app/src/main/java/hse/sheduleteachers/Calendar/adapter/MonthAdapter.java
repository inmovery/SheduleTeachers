package hse.sheduleteachers.Calendar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import hse.sheduleteachers.Calendar.adapter.DaysAdapter;
import hse.sheduleteachers.Calendar.adapter.viewholder.MonthHolder;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.model.Month;
import hse.sheduleteachers.Calendar.selection.BaseSelectionManager;
import hse.sheduleteachers.Calendar.settings.lists.DisabledDaysCriteria;
import hse.sheduleteachers.Calendar.utils.CalendarUtils;
import hse.sheduleteachers.Calendar.utils.DayFlag;
import hse.sheduleteachers.Calendar.view.CalendarView;
import hse.sheduleteachers.Calendar.view.ItemViewType;
import hse.sheduleteachers.Calendar.view.delegate.DayDelegate;
import hse.sheduleteachers.Calendar.view.delegate.DayOfWeekDelegate;
import hse.sheduleteachers.Calendar.view.delegate.MonthDelegate;
import hse.sheduleteachers.Calendar.view.delegate.OtherDayDelegate;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class MonthAdapter extends RecyclerView.Adapter<MonthHolder> {

    private final List<Month> months;

    private MonthDelegate monthDelegate;

    private CalendarView calendarView;
    private BaseSelectionManager selectionManager;
    private hse.sheduleteachers.Calendar.adapter.DaysAdapter daysAdapter;

    private MonthAdapter(List<Month> months,
                         MonthDelegate monthDelegate,
                         CalendarView calendarView,
                         BaseSelectionManager selectionManager) {
        setHasStableIds(true);
        this.months = months;
        this.monthDelegate = monthDelegate;
        this.calendarView = calendarView;
        this.selectionManager = selectionManager;
    }

    public void setSelectionManager(BaseSelectionManager selectionManager) {
        this.selectionManager = selectionManager;
    }

    public BaseSelectionManager getSelectionManager() {
        return selectionManager;
    }

    @Override
    public MonthHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        daysAdapter = new DaysAdapter.DaysAdapterBuilder()
                .setDayOfWeekDelegate(new DayOfWeekDelegate(calendarView))
                .setOtherDayDelegate(new OtherDayDelegate(calendarView))
                .setDayDelegate(new DayDelegate(calendarView, this))
                .setCalendarView(calendarView)
                .createDaysAdapter();
        return monthDelegate.onCreateMonthHolder(daysAdapter, parent, viewType);
    }

    @Override
    public void onBindViewHolder(MonthHolder holder, int position) {
        final Month month = months.get(position);
        monthDelegate.onBindMonthHolder(month, holder, position);
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ItemViewType.MONTH;
    }

    @Override
    public long getItemId(int position) {
        return months.get(position).getFirstDay().getCalendar().getTimeInMillis();
    }

    public List<Month> getData() {
        return months;
    }

    public static class MonthAdapterBuilder {

        private List<Month> months;
        private MonthDelegate monthDelegate;
        private CalendarView calendarView;
        private BaseSelectionManager selectionManager;

        public MonthAdapterBuilder setMonths(List<Month> months) {
            this.months = months;
            return this;
        }

        public MonthAdapterBuilder setMonthDelegate(MonthDelegate monthHolderDelegate) {
            this.monthDelegate = monthHolderDelegate;
            return this;
        }

        public MonthAdapterBuilder setCalendarView(CalendarView calendarView) {
            this.calendarView = calendarView;
            return this;
        }

        public MonthAdapterBuilder setSelectionManager(BaseSelectionManager selectionManager) {
            this.selectionManager = selectionManager;
            return this;
        }

        public MonthAdapter createMonthAdapter() {
            return new MonthAdapter(months,
                    monthDelegate,
                    calendarView,
                    selectionManager);
        }
    }

    public void setWeekendDays(Set<Long> weekendDays) {
        setDaysAccordingToSet(weekendDays, DayFlag.WEEKEND);
    }

    public void setDisabledDays(Set<Long> disabledDays) {
        setDaysAccordingToSet(disabledDays, DayFlag.DISABLED);
    }

    public void setConnectedCalendarDays(Set<Long> connectedCalendarDays) {
        setDaysAccordingToSet(connectedCalendarDays, DayFlag.FROM_CONNECTED_CALENDAR);
    }

    public void setDisabledDaysCriteria(DisabledDaysCriteria criteria){
        for (Month month : months) {
            for (Day day : month.getDays()) {
                if(!day.isDisabled()){
                    day.setDisabled(CalendarUtils.isDayDisabledByCriteria(day, criteria));
                }
            }
        }
        notifyDataSetChanged();
    }

    private void setDaysAccordingToSet(Set<Long> days, DayFlag dayFlag) {
        if (days != null && !days.isEmpty()) {
            for (Month month : months) {
                for (Day day : month.getDays()) {
                    switch (dayFlag) {
                        case WEEKEND:
                            day.setWeekend(days.contains(day.getCalendar().get(Calendar.DAY_OF_WEEK)));
                            break;

                        case DISABLED:
                            day.setDisabled(CalendarUtils.isDayInSet(day, days));
                            break;

                        case FROM_CONNECTED_CALENDAR:
                            day.setFromConnectedCalendar(CalendarUtils.isDayInSet(day, days));
                            break;
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}
