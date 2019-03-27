package hse.sheduleteachers.Calendar.view.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hse.sheduleteachers.Calendar.view.delegate.BaseDelegate;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.adapter.MonthAdapter;
import hse.sheduleteachers.Calendar.adapter.viewholder.DayHolder;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.selection.BaseSelectionManager;
import hse.sheduleteachers.Calendar.selection.MultipleSelectionManager;
import hse.sheduleteachers.Calendar.view.CalendarView;

public class DayDelegate extends BaseDelegate {

    private MonthAdapter monthAdapter;

    public DayDelegate(CalendarView calendarView, MonthAdapter monthAdapter) {
        this.calendarView = calendarView;
        this.monthAdapter = monthAdapter;
    }

    public DayHolder onCreateDayHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_day, parent, false);
        return new DayHolder(view, calendarView);
    }

    public void onBindDayHolder(final RecyclerView.Adapter daysAdapter, final Day day,
                                final DayHolder holder, final int position) {
        final BaseSelectionManager selectionManager = monthAdapter.getSelectionManager();
        holder.bind(day, selectionManager);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!day.isDisabled()) {
                    selectionManager.toggleDay(day);
                    if (selectionManager instanceof MultipleSelectionManager) {
                        daysAdapter.notifyItemChanged(position);
                    } else {
                        monthAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
