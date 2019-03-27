package hse.sheduleteachers.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Teacher withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher withName(String name) {
        this.name = name;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.name = token;
    }

    public Teacher withToken(String token) {
        this.name = token;
        return this;
    }

}
