package hse.sheduleteachers.Calendar.view.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.adapter.DaysAdapter;
import hse.sheduleteachers.Calendar.adapter.viewholder.MonthHolder;
import hse.sheduleteachers.Calendar.settings.SettingsManager;
import hse.sheduleteachers.Calendar.model.Month;

public class MonthDelegate {

    private SettingsManager appearanceModel;

    public MonthDelegate(SettingsManager appearanceModel) {
        this.appearanceModel = appearanceModel;
    }

    public MonthHolder onCreateMonthHolder(DaysAdapter adapter, ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.view_month, parent, false);
        final MonthHolder holder = new MonthHolder(view, appearanceModel);
        holder.setDayAdapter(adapter);
        return holder;
    }

    public void onBindMonthHolder(Month month, MonthHolder holder, int position) {
        holder.bind(month);
    }
}
