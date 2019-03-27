package hse.sheduleteachers.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import hse.sheduleteachers.Api.ApiService;
import hse.sheduleteachers.Api.RetroClient;
import hse.sheduleteachers.Calendar.view.CalendarView;
import hse.sheduleteachers.Model.Lesson;
import hse.sheduleteachers.Model.LessonList;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Storage.SessionManager;
import hse.sheduleteachers.TimetableAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hse.sheduleteachers.Storage.SessionManager.getDataInt;
import static hse.sheduleteachers.Storage.SessionManager.getDataString;
import static hse.sheduleteachers.Storage.SessionManager.saveData;

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Lesson> lessonsList;

    //private RecyclerView fetch_data;
    private ShimmerRecyclerView shimmerRecycler;

    private TimetableAdapter adapter;

    //HEADER
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    private boolean isExpanded = false;//проверка на состояние календаря: "Показать/Скрыть"

    private ImageView calCancel;//иконка "Закрыть календарь"

    private ImageView calDone;//иконка "Выбрать дату"

    private CalendarView calendarView;

    private ImageView arrow;//стрелочка для календоря

    private CoordinatorLayout tor;

    SessionManager sessionManager;

    private TextView text_date;

    private TextView mCheckEmptyPairs;

    //private FrameLayout frameLayout;//панелька со стрелочками календаря

    //private int check_single = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));//задали шапку

        lessonsList = new ArrayList<>();

        appBarLayout = findViewById(R.id.app_bar_layout);

        calCancel = (ImageView) findViewById(R.id.cal_cancel);
        calDone = (ImageView) findViewById(R.id.cal_done);
        calendarView = (CalendarView) findViewById(R.id.calendar_p);

        //fetch_data = (RecyclerView) findViewById(R.id.fetch_today);
        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.fetch_today);

        tor = (CoordinatorLayout)findViewById(R.id.tor);

        String teachersID = String.valueOf(getDataInt("TEACHER_ID"));

        text_date = (TextView)findViewById(R.id.text_date);


        mCheckEmptyPairs = (TextView)findViewById(R.id.text_empty_pairs);

        //frameLayout = (FrameLayout)findViewById(R.id.navigation_buttons_bar);

//        if(getDataString("CALENDAR") == "SINGLE"){
//            frameLayout.setVisibility(View.INVISIBLE);
//            check_single = 1;
//        }

        sessionManager = new SessionManager(this);

        //поставили слушатели на кнопки в календаре
        calCancel.setOnClickListener(this);
        calDone.setOnClickListener(this);

        //получение сегодняшней даты по Перми
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        f.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
        String day = f.format(new Date());

        getTimetable(day,teachersID);

        //стрелочка календаря
        arrow = findViewById(R.id.date_picker_arrow);
        RelativeLayout datePickerButton = findViewById(R.id.date_picker_button);

        //сворачивание стрелочки в календаре
        datePickerButton.setOnClickListener(v -> {
            float rotation = isExpanded ? 0 : 180;
            ViewCompat.animate(arrow).rotation(rotation).start();

            isExpanded = !isExpanded;
            appBarLayout.setExpanded(isExpanded, true);
        });
    }

    /*
    * Получение расписания
    * */

    private void getTimetable(String day, String id){

        text_date.setVisibility(View.VISIBLE);
        mCheckEmptyPairs.setVisibility(View.INVISIBLE);

        shimmerRecycler.showShimmerAdapter();

        String[] parts = day.split("\\.");
        String month = "";
        if(Integer.parseInt(parts[1]) == 1) month = "января";
        else if(Integer.parseInt(parts[1]) == 2) month = "февраля";
        else if(Integer.parseInt(parts[1]) == 3) month = "марта";
        else if(Integer.parseInt(parts[1]) == 4) month = "апреля";
        else if(Integer.parseInt(parts[1]) == 5) month = "мая";
        else if(Integer.parseInt(parts[1]) == 6) month = "июня";
        else if(Integer.parseInt(parts[1]) == 7) month = "июля";
        else if(Integer.parseInt(parts[1]) == 8) month = "августа";
        else if(Integer.parseInt(parts[1]) == 9) month = "сентября";
        else if(Integer.parseInt(parts[1]) == 10) month = "октября";
        else if(Integer.parseInt(parts[1]) == 11) month = "ноября";
        else if(Integer.parseInt(parts[1]) == 12) month = "декабря";

        String dateStr = day;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {}
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int temp = calendar.get(Calendar.DAY_OF_WEEK);

        String day_of_week = "";
        if(temp == 1) day_of_week = "Воскресенье";
        else if(temp == 2) day_of_week = "Понедельник";
        else if(temp == 3) day_of_week = "Вторник";
        else if(temp == 4) day_of_week = "Среда";
        else if(temp == 5) day_of_week = "Четверг";
        else if(temp == 6) day_of_week = "Пятница";
        else if(temp == 7) day_of_week = "Суббота";

        text_date.setText(day_of_week + ", " + Integer.parseInt(parts[0])+ " " + month);


        ApiService api = RetroClient.getApiService();//создание объекта интерфейса
        /*Обращение к JSON*/
        Call<LessonList> call = api.getLessonsJSON(day,id,"1","1");

        //Запрос
        call.enqueue(new Callback<LessonList>() {
            //успешно
            @Override
            public void onResponse(Call<LessonList> call, Response<LessonList> response) {

                if(response.isSuccessful()){
                    //получение списка учителей в качестве объектов Teacher
                    lessonsList = response.body().getLessons();

                    if(lessonsList.size() == 0){
                        mCheckEmptyPairs.setVisibility(View.VISIBLE);
                    } else {
                        mCheckEmptyPairs.setVisibility(View.INVISIBLE);
                    }

                    // вывод расписания в RecyclerView
                    shimmerRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new TimetableAdapter(lessonsList);
                    adapter.notifyDataSetChanged();
                    shimmerRecycler.setAdapter(adapter);
                } else {
                    //сообщение об ошибке получение JSON
                    Toast.makeText(getApplicationContext(), "Ошибка в получении JSON", Toast.LENGTH_SHORT).show();
                }
            }
            //не успешно
            @Override
            public void onFailure(Call<LessonList> call, Throwable t) {

                Snackbar snackbar = Snackbar.make(tor, "Нет инетрнет соединения!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("ОБНОВИТЬ", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                // Message text color
                snackbar.setActionTextColor(Color.rgb(124, 12, 176));



                // Action button text color
                View snackBarView = snackbar.getView();

                TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();

                shimmerRecycler.hideShimmerAdapter();
                //shimmerRecycler.setDemoShimmerDuration(1000);

                //Toast.makeText(getApplicationContext(), "Не успешно", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //Кнопки "Закрыть календарь" и "Выбрать дату"
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cal_cancel) {
            //скрытие календаря и изменение стрелочки
            float rotation = isExpanded ? 0 : 180;
            ViewCompat.animate(arrow).rotation(rotation).start();

            isExpanded = false;//календарь свёрнут
            appBarLayout.setExpanded(false, true);
        } else if (id == R.id.cal_done) {
            //запоминание выбранной даты
            doneClick();

            //скрытие календаря и изменение стрелочки
            float rotation = isExpanded ? 0 : 180;
            ViewCompat.animate(arrow).rotation(rotation).start();

            isExpanded = false;//календарь свёрнут
            appBarLayout.setExpanded(false, true);
        }
    }

    private void doneClick() {
        String teachersID = String.valueOf(getDataInt("TEACHER_ID"));
        String day = calendarView.getSelectedDays().get(0).toString();

//        Calendar newCal = new GregorianCalendar();
//        newCal.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
//        newCal.setTime(newCal.getTime());
//        int num_day = newCal.get(Calendar.DAY_OF_WEEK);
//        text_date.setText(num_day);

        this.setTitle(day);
        getTimetable(day,teachersID);
        //Обновление RecycleViews
        //...
    }




    /*
    * Настройки
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainScreen.this, SettingsScreen.class));
            Animatoo.animateFade(this);
        }

        return super.onOptionsItemSelected(item);
    }


    /*
     * Обновление активити
     * */
    private void Reload() {
        Intent intent = new Intent(MainScreen.this, MainScreen.class);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sessionManager.createSession();
        saveData("NAME",getDataString("NAME"));
        saveData("TEACHER_ID",getDataInt("TEACHER_ID"));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}
