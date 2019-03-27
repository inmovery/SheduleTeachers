package hse.sheduleteachers.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonList {

    @SerializedName("days")
    @Expose
    private ArrayList<Lesson> days = null;

    public ArrayList<Lesson> getLessons() {
        return days;
    }

    public void setLessons(ArrayList<Lesson> days) {
        this.days = days;
    }

}
