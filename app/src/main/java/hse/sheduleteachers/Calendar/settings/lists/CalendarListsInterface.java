package hse.sheduleteachers.Calendar.settings.lists;

import hse.sheduleteachers.Calendar.settings.lists.DisabledDaysCriteria;
import hse.sheduleteachers.Calendar.settings.lists.connected_days.ConnectedDays;
import hse.sheduleteachers.Calendar.settings.lists.connected_days.ConnectedDaysManager;

import java.util.Set;

public interface CalendarListsInterface {

    Set<Long> getDisabledDays();

    ConnectedDaysManager getConnectedDaysManager();

    Set<Long> getWeekendDays();

    hse.sheduleteachers.Calendar.settings.lists.DisabledDaysCriteria getDisabledDaysCriteria();

    void setDisabledDays(Set<Long> disabledDays);

    void setWeekendDays(Set<Long> weekendDays);

    void setDisabledDaysCriteria(DisabledDaysCriteria criteria);

    void addConnectedDays(ConnectedDays connectedDays);
}
