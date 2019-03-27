package hse.sheduleteachers.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeacherList {

    @SerializedName("teachers")
    @Expose
    private ArrayList<Teacher> teachers = null;

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public TeacherList withTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

}
