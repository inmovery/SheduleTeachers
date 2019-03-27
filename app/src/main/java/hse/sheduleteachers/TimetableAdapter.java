package hse.sheduleteachers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hse.sheduleteachers.Model.Lesson;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableViewHolder> {

    Context mContext;
    ArrayList<Lesson> mLessonsList;

    public TimetableAdapter(ArrayList<Lesson> lessonsList){
        mLessonsList = lessonsList;
    }

    //Инициализация holder
    @Override
    public TimetableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        TimetableViewHolder holder = new TimetableViewHolder(itemView);
        return holder;
    }
    //Привязка данных к элементам
    @Override
    public void onBindViewHolder(TimetableViewHolder holder, int position) {
        Lesson lesson = mLessonsList.get(position);
        holder.mLessonsName.setText(lesson.getNameLesson());
        String[] parts = lesson.getTime().split("-");
        holder.mPrevTime.setText(parts[0]);
        holder.mNextTime.setText(parts[1]);
        holder.mLessonsWho.setText(lesson.getNameUsers());
        holder.mCampus.setText(lesson.getCampus()+ " корпус");
        holder.mLectureHall.setText(lesson.getLecture() + " ауд.");
        holder.mLessonsType.setText(lesson.getType());
    }

    @Override
    public int getItemCount() {
        return mLessonsList.size();
    }

}
