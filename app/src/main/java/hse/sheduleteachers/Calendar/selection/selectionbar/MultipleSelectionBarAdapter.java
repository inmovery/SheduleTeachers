package hse.sheduleteachers.Calendar.selection.selectionbar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarContentItem;
import hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarItem;
import hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarTitleItem;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Calendar.model.Day;
import hse.sheduleteachers.Calendar.view.CalendarView;
import hse.sheduleteachers.Calendar.view.customviews.CircleAnimationTextView;

import java.util.ArrayList;
import java.util.List;

public class MultipleSelectionBarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarItem> items;
    private CalendarView calendarView;
    private ListItemClickListener listItemClickListener;

    private final static int VIEW_TYPE_TITLE = 0;
    private final static int VIEW_TYPE_CONTENT = 1;

    public MultipleSelectionBarAdapter(CalendarView calendarView, ListItemClickListener listItemClickListener) {
        items = new ArrayList<>();
        this.calendarView = calendarView;
        this.listItemClickListener = listItemClickListener;
    }

    public void setData(List<SelectionBarItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setListItemClickListener(ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multiple_selection_bar_title, parent, false));
        } else {
            return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multiple_selection_bar_content, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarTitleItem) {
            return VIEW_TYPE_TITLE;
        } else {
            return VIEW_TYPE_CONTENT;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_TITLE) {
            ((TitleViewHolder) holder).bind(position);
        } else {
            ((ContentViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class TitleViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void bind(int position) {
            hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarTitleItem selectionBarTitleItem = (SelectionBarTitleItem) items.get(position);
            tvTitle.setText(selectionBarTitleItem.getTitle());
            tvTitle.setTextColor(calendarView.getSelectionBarMonthTextColor());
        }
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        final CircleAnimationTextView catvDay;

        public ContentViewHolder(View itemView) {
            super(itemView);
            catvDay = (CircleAnimationTextView) itemView.findViewById(R.id.catv_day);
        }

        public void bind(int position) {
            final hse.sheduleteachers.Calendar.selection.selectionbar.SelectionBarContentItem selectionBarContentItem = (SelectionBarContentItem) items.get(position);
            catvDay.setText(String.valueOf(selectionBarContentItem.getDay().getDayNumber()));
            catvDay.setTextColor(calendarView.getSelectedDayTextColor());
            catvDay.showAsSingleCircle(calendarView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listItemClickListener != null) {
                        listItemClickListener.onMultipleSelectionListItemClick(selectionBarContentItem.getDay());
                    }
                }
            });
        }
    }

    public interface ListItemClickListener {
        void onMultipleSelectionListItemClick(Day day);
    }
}
