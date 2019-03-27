package hse.sheduleteachers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TimetableViewHolder extends RecyclerView.ViewHolder {

    TextView mLessonsName;
    TextView mPrevTime;
    TextView mNextTime;
    TextView mLessonsWho;
    TextView mCampus;
    TextView mLectureHall;
    TextView mLessonsType;

    public TimetableViewHolder(View itemView) {
        super(itemView);

        mLessonsName = (TextView)itemView.findViewById(R.id.lessons_name);
        mPrevTime = (TextView)itemView.findViewById(R.id.prev_time);
        mNextTime = (TextView)itemView.findViewById(R.id.next_time);
        mLessonsWho = (TextView)itemView.findViewById(R.id.lessons_who);
        mCampus = (TextView)itemView.findViewById(R.id.campus);
        mLectureHall = (TextView)itemView.findViewById(R.id.lecture_hall);
        mLessonsType = (TextView)itemView.findViewById(R.id.lessons_type);
    }

}


