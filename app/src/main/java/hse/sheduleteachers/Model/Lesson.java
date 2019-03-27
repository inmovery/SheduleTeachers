package hse.sheduleteachers.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lesson {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("name_day")
    @Expose
    private String nameDay;
    @SerializedName("name_teachers")
    @Expose
    private String nameTeachers;
    @SerializedName("name_users")
    @Expose
    private String nameUsers;
    @SerializedName("name_lesson")
    @Expose
    private String nameLesson;
    @SerializedName("lecture")
    @Expose
    private String lecture;
    @SerializedName("campus")
    @Expose
    private String campus;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("type")
    @Expose
    private String type;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNameDay() {
        return nameDay;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }

    public String getNameTeachers() {
        return nameTeachers;
    }

    public void setNameTeachers(String nameTeachers) {
        this.nameTeachers = nameTeachers;
    }

    public String getNameUsers() {
        return nameUsers;
    }

    public void setNameUsers(String nameUsers) {
        this.nameUsers = nameUsers;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
