package hse.sheduleteachers.Api;

import hse.sheduleteachers.Model.LessonList;
import hse.sheduleteachers.Model.TeacherList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /* Получение списка преподавателей */
    @GET("/tparser/teach.php")
    Call<TeacherList> getTeachJSON();

    /* Получение расписания по сформированному запросу */
    @GET("/tparser/teachers.php")
    Call<LessonList> getLessonsJSON(
            @Query("day") String day,
            @Query("teach") String id,
            @Query("type") String type,
            @Query("update") String update
    );


}
