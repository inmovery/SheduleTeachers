package hse.sheduleteachers.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import hse.sheduleteachers.Feedback;
import hse.sheduleteachers.R;
import hse.sheduleteachers.SettingsDesign;
import hse.sheduleteachers.Storage.SessionManager;

import static hse.sheduleteachers.Storage.SessionManager.getDataInt;
import static hse.sheduleteachers.Storage.SessionManager.getDataString;
import static hse.sheduleteachers.Storage.SessionManager.saveData;

public class SettingsScreen extends AppCompatActivity {

    private Button mLogout;

    SessionManager sessionManager;//для работы с SharedPreferences

    private TextView mFio;//ФИО преподавателя

    private LinearLayout mFrame;//основная панель для вывода snackbar

    private SwitchButton mSwitchPushNotification;//переключатель уведомлений

    private LinearLayout mSettingsCalendar;//настройка уведомлений

    private LinearLayout mSettingsFeedback;//обратная связь

    private LinearLayout mSettingsDesign;//настройка интерфейса

    private ImageView mAddPush;//настройка дополнительных уведомлений

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        //Добавление кнопки "Назад"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Установка названия активити
        setTitle("Настройки");

        /*Инициализация Firebase*/
        FirebaseApp.initializeApp(this);

        sessionManager = new SessionManager(this);//для работы с SharedPreferences

        mFrame = (LinearLayout)findViewById(R.id.main_frame);
        mLogout = findViewById(R.id.logout_btn);
        mFio = (TextView)findViewById(R.id.initials);
        mSwitchPushNotification = (SwitchButton)findViewById(R.id.show_hide_push);

        mSettingsCalendar = (LinearLayout)findViewById(R.id.settings_calendar);
        mSettingsFeedback = (LinearLayout)findViewById(R.id.feedback);
        mSettingsDesign = (LinearLayout)findViewById(R.id.settings_design);

        mAddPush = (ImageView)findViewById(R.id.dop_push);
        if(getDataString("TOKEN") != "") saveData("PUSH", 1);
        else saveData("PUSH", 0);

        //Работа с переключателем уведомлений
        if(getDataInt("PUSH") != 0) {
            mSwitchPushNotification.setChecked(true);
            //mAddPush.setVisibility(View.VISIBLE);
        } else mSwitchPushNotification.setChecked(false);

        //mSwitchPushNotification.toggle(true);
        //mSwitchPushNotification.setShadowEffect(true);
        mSwitchPushNotification.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if(mSwitchPushNotification.isChecked()){ //если нажали "Включить уведомления"
                    saveData("PUSH",1);
                    saveData("TOKEN","awdawd");
                    FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( SettingsScreen.this,  new OnSuccessListener<InstanceIdResult>() {
                        @Override
                        public void onSuccess(InstanceIdResult instanceIdResult) {
                            String token = instanceIdResult.getToken();
                            registerToken(getApplicationContext(),token);
                        }
                    });

                    //mAddPush.setVisibility(View.VISIBLE);
                    //добавили настройки доп. уведомлений

                    Snackbar snackbar = Snackbar.make(mFrame, "Уведомления включены!", Snackbar.LENGTH_SHORT);
                    //цвет шрифта во всплывающем окне
                    snackbar.setActionTextColor(Color.rgb(124, 12, 176));

                    View snackBarView = snackbar.getView();
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();

                } else { //если нажали "Отключить уведомления"

                    saveData("PUSH",0);
                    saveData("TOKEN","");
                    deleteToken(getApplicationContext());

                    mAddPush.setVisibility(View.INVISIBLE);//убрали доп. уведомления.
                    //очистили настройки доп. уведомлений

                    Snackbar snackbar = Snackbar.make(mFrame, "Уведомления отключены!", Snackbar.LENGTH_SHORT);
                    //цвет шрифта во всплывающем окне
                    snackbar.setActionTextColor(Color.rgb(124, 12, 176));

                    View snackBarView = snackbar.getView();
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.MAGENTA);
                    snackbar.show();
                }
                //Toast.makeText(getApplicationContext(), String.valueOf(mSwitchPushNotification.isChecked()), Toast.LENGTH_SHORT).show();
            }
        });


        //переход на экран настройки календаря
        mSettingsCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsScreen.this, SettingsCalendar.class));
            }
        });

        //переход на экран обратной связи
        mSettingsFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsScreen.this, Feedback.class));
            }
        });

        //переход на экран настройки интерфейса(дизайна)
        mSettingsDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsScreen.this, SettingsDesign.class));
            }
        });

        //переход на экран с дополнительными настройками уведомлений
        mAddPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        });


        //Установка ФИО преподавателя
        mFio.setText(getDataString("NAME"));

        //Обработка кнопки "Выйти"
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Animatoo.animateFade(SettingsScreen.this);
            }
        });






    }


    /*
    * Реистрация мобильного устройства (включение уведомлений)
    * */
    private void registerToken(Context context, final String token){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://devredowl.ru/tparser/push/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Если запрос прошёл успешно
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Обработка ошибки при отправке запроса
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Token",token);
                params.put("Id",String.valueOf(getDataInt("TEACHER_ID")));
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

    }

    /*
     * Удаление токена мобильного устройства (отключение уведомлений)
     * */
    private void deleteToken(Context context){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://devredowl.ru/tparser/push/delete.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Если запрос прошёл успешно
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Обработка ошибки при отправке запроса
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Id",String.valueOf(getDataInt("TEACHER_ID")));
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

    }

    /*
    * Обработка кнопки "Назад"
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                saveData("NAME",getDataString("NAME"));
                saveData("TEACHER_ID",getDataInt("TEACHER_ID"));
                saveData("TOKEN",getDataString("TOKEN"));
                saveData("PUSH",getDataInt("PUSH"));
                finish();
                Animatoo.animateFade(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData("NAME",getDataString("NAME"));
        saveData("TEACHER_ID",getDataInt("TEACHER_ID"));
        saveData("TOKEN",getDataInt("TOKEN"));
    }

}
