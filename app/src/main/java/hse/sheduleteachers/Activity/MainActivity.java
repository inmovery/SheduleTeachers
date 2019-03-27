package hse.sheduleteachers.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

import hse.sheduleteachers.Dialog.OnSpinerItemClick;
import hse.sheduleteachers.Dialog.SpinnerDialog;
import hse.sheduleteachers.R;
import hse.sheduleteachers.Api.ApiService;
import hse.sheduleteachers.Api.RetroClient;
import hse.sheduleteachers.Model.Teacher;
import hse.sheduleteachers.Model.TeacherList;
import hse.sheduleteachers.Storage.SessionManager;
import hse.sheduleteachers.Utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hse.sheduleteachers.Storage.SessionManager.saveData;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Teacher> teacherList;
    private ArrayList<String> items;

    private SpinnerDialog spinnerDialog;
    String[] teachers;

    private LinearLayout linearLayout;
    private Button button;

    public int check = 0;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        linearLayout = (LinearLayout) findViewById(R.id.coordinator);

        teacherList = new ArrayList<>();

        button = (Button)findViewById(R.id.show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(InternetConnection.checkConnection(getApplicationContext())) {
                    ApiService api = RetroClient.getApiService();//создание объекта интерфейса
                    /*Обращение к JSON*/
                    Call<TeacherList> call = api.getTeachJSON();

                    //Запрос
                    call.enqueue(new Callback<TeacherList>() {
                        //успешно
                        @Override
                        public void onResponse(Call<TeacherList> call, Response<TeacherList> response) {
                            if (response.isSuccessful()) {
                                //получение списка учителей в качестве объектов Teacher
                                teacherList = response.body().getTeachers();
                                //создание массива для ListView
                                teachers = new String[teacherList.size()];
                                //заполнение массва ФИО преподавателей
                                for (int i = 0; i < teacherList.size(); i++) {
                                    teachers[i] = teacherList.get(i).getName();
                                }
                                //создание диалогового окна с выбором преподавателей
                                spinnerDialog = new SpinnerDialog(MainActivity.this, teachers,
                                        "Поиск преподавателя");
                                //обработка нажатия на выбраннный элемент (item преподавателя)
                                spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item, int position) {

                                        sessionManager.createSession();
                                        saveData("NAME",item);
                                        saveData("TEACHER_ID",position+1);
                                        //saveData("CALENDAR", "SINGLE");
                                        startActivity(new Intent(MainActivity.this, MainScreen.class));
                                        Animatoo.animateFade(MainActivity.this);
                                    }
                                });

                                spinnerDialog.showSpinerDialog();

                                //Toast.makeText(getApplicationContext(), string_show, Toast.LENGTH_SHORT).show();


                            } else {
                                //сообщение об ошибке получение JSON
                            }
                        }

                        //не успешно
                        @Override
                        public void onFailure(Call<TeacherList> call, Throwable t) {

                        }
                    });
                } else {
                    Snackbar snackbar = Snackbar.make(linearLayout, "Нет инетрнет соединения!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("ОБНОВИТЬ", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Reload();
                                }
                            });

                    // Message text color
                    snackbar.setActionTextColor(Color.rgb(124, 12, 176));

                    // Action button text color
                    View snackBarView = snackbar.getView();
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });
    }

    /*
    * Обновление активити
    * */
    private void Reload() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }


}
